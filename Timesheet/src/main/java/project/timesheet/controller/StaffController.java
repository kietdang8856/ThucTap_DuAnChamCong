package project.timesheet.controller;

import org.antlr.v4.runtime.misc.NotNull;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.timesheet.models.*;
import project.timesheet.services.ChucVuService;
import project.timesheet.services.NhanVienService;
import project.timesheet.services.StaffService;
import project.timesheet.services.VanPhongService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private NhanVienService userService;
    @Autowired
    private VanPhongService vanPhongService;
    @Autowired
    private ChucVuService chucVuService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @GetMapping
    public String staffs( Model model,
                          @RequestParam(defaultValue = "0") Integer pageNo,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "Id") String sortBy){
        List<NhanVien> allStaff = staffService.getAllStaff(pageNo, pageSize, sortBy);
        model.addAttribute("allStaff", allStaff);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", staffService.getAllStaff().size() / pageSize);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "admin/staff/list";
    }
    @GetMapping("/search")
    public String searchStaffs(Model model,
                               @RequestParam String keyword,
                               @RequestParam(defaultValue = "0") Integer pageNo,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "Id") String sortBy) {
        List<NhanVien> searchedStaffs = staffService.searchStaffs(keyword);
        model.addAttribute("allStaff", searchedStaffs);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", searchedStaffs.size() / pageSize);
        return "admin/staff/list";
    }

    @GetMapping("/add")
    public String showRegistrationForm(Model model) {
        List<Role> roles = userService.getAllRoles();
        List<VanPhong> vanPhongs = vanPhongService.getAllVanPhongs();
        List<ChucVu> chucVus = chucVuService.getALL();
        NhanVien nhanVien = new NhanVien();
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("vanPhongs", vanPhongs);
        model.addAttribute("chucVus", chucVus);
        model.addAttribute("roles", roles);
        return "admin/staff/add";
    }

    @PostMapping("/add")
    public String registerUser(
            @RequestParam("tenNV") String tenNV,
            @RequestParam("username") String username,
            @RequestParam("sdt") String sdt,
            @RequestParam("diaChi") String diaChi,
            @RequestParam("avatar") MultipartFile avatarFile,
            @RequestParam("ngayBatDauLam") Date ngayBatDauLam,
            @RequestParam("gioiTinh") int gioiTinh,
            @RequestParam("email") String email,
            @RequestParam("chucvuId") Integer chucvuId,
            @RequestParam("vpchinhId") Integer vpId ,
            @RequestParam("password") String password,
            @RequestParam("roles") List<Long> roleIds,
            RedirectAttributes redirectAttributes,
            Model model
    )throws IOException
    {
        List<Role> roles = userService.getAllRoles();
        List<VanPhong> vanPhongs = vanPhongService.getAllVanPhongs();
        List<ChucVu> chucVus = chucVuService.getALL();
        NhanVien nhanVien = new NhanVien();
        nhanVien.setTenNV(tenNV);
        nhanVien.setSdt(sdt);
        nhanVien.setDiaChi(diaChi);
        nhanVien.setNgayBatDauLam(ngayBatDauLam);
        nhanVien.setGioiTinh(gioiTinh);
        nhanVien.setEmail(email);
        nhanVien.setUsername(username);
        nhanVien.setPassword(passwordEncoder.encode(password));
        nhanVien.setEnabled(1);
        //kiểm tra và bắ lỗi
        if (userService.findByUsername(username) != null) {
            model.addAttribute("usernameError", "Username đã tồn tại. Vui lòng chọn username khác.");
        }
        if (userService.existsAdmin() && roleIds.contains(userService.getRoleByName("ADMIN").getId())) {
            model.addAttribute("roleError", "Chỉ có một tài khoản được phép có role ADMIN");}
        // Kiểm tra email đã tồn tại hay chưa
        if (userService.existsByEmail(email)) {
            model.addAttribute("emailError", "Email đã tồn tại. Vui lòng chọn email khác.");
        }
        // Kiểm tra nếu password trống
        if (password.isEmpty()) {
            model.addAttribute("passwordError", "Mật khẩu không được để trống.");
        }
        // Kiểm tra nếu có bất kỳ lỗi nào
        if (model.containsAttribute("usernameError") || model.containsAttribute("emailError")|| model.containsAttribute("roleError") || model.containsAttribute("passwordError")) {
            model.addAttribute("nhanVien", nhanVien); // Đưa thông tin đã nhập vào lại form
            model.addAttribute("roles", userService.getAllRoles());
            model.addAttribute("chucVus", chucVuService.getALL());
            model.addAttribute("vanPhongs", vanPhongService.getAllVanPhongs());
            return "admin/staff/add"; // Trả về lại trang đăng ký nếu có lỗi
        }

        try {

            if (!avatarFile.isEmpty()) {
                String originalFilename = StringUtils.cleanPath(avatarFile.getOriginalFilename());
                String fileExtension = FilenameUtils.getExtension(originalFilename);

                // Tạo tên file duy nhất , sử dụng UUID
                String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

                String uploadDir = "src/main/resources/static/user/img";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Lưu tệp ảnh vào thư mục
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(avatarFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                nhanVien.setAvatarPath(uniqueFileName); // Lưu tên file duy nhất vào database
            }
        } catch (IOException e) {
            // Xử lý lỗi đọc/lưu file
            e.printStackTrace();
        }


        List<UserRole> userRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            Role role = userService.getRoleById(roleId);
            if (role!= null) {
                UserRole userRole = new UserRole(nhanVien, role);
                userRoles.add(userRole);
            }
        }
        ChucVu chucVu = chucVuService.getChucVuById(chucvuId);
        Optional<VanPhong> vanPhong = vanPhongService.getVanPhongById(vpId);
        VanPhong vp = vanPhong.get();
        nhanVien.setChucvu(chucVu); // Thiết lập chức vụ cho nhân viên
        nhanVien.setVpLamViecChinh(vp); // Thiết lập văn phòng chính
        nhanVien.getUserRoles().clear();
        userRoles.forEach(userRole -> nhanVien.getUserRoles().add(userRole));
        userService.saveUser(nhanVien);

        return "redirect:/admin/staff";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        NhanVien user = userService.findById(id);
        List<Role> roles = userService.getAllRoles();
        List<Role> userRoles = user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toList());
        List<VanPhong> vanPhongs = vanPhongService.getAllVanPhongs();
        List<ChucVu> chucVus = chucVuService.getALL();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("vanPhongs", vanPhongs);
        model.addAttribute("chucVus", chucVus);
        return "admin/staff/edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(
            @PathVariable int id,
            @RequestParam("tenNV") String tenNV,
            @RequestParam("sdt") String sdt,
            @RequestParam("diaChi") String diaChi,
            @RequestParam("avatar") MultipartFile avatarFile,
            @RequestParam("ngayBatDauLam") Date ngayBatDauLam,
            @RequestParam("gioiTinh") int gioiTinh,
            @RequestParam("chucvuId") Integer chucvuId,
            @RequestParam("vpchinhId") Integer vpId,
            @RequestParam("password") String password,
            @RequestParam("roles") List<Long> roleIds,
            RedirectAttributes redirectAttributes,
            Model model
    ) throws IOException {
        NhanVien existingNhanVien = userService.findById(id);

        if (existingNhanVien == null) {
            model.addAttribute("error", "User not found with ID: " + id);
            return "redirect:/admin/staff";
        }

        existingNhanVien.setTenNV(tenNV);
        existingNhanVien.setSdt(sdt);
        existingNhanVien.setDiaChi(diaChi);
        existingNhanVien.setNgayBatDauLam(ngayBatDauLam);
        existingNhanVien.setGioiTinh(gioiTinh);
        existingNhanVien.setEnabled(1);
        // Chỉ cập nhật mật khẩu nếu người dùng nhập mật khẩu mới
        if (StringUtils.hasText(password)) {
            existingNhanVien.setPassword(passwordEncoder.encode(password));
        }
        if (userService.existsOtherAdmin(id) && roleIds.contains(userService.getRoleByName("ADMIN").getId())) {
            redirectAttributes.addFlashAttribute("roleError", "Chỉ có một tài khoản được phép có role ADMIN");
            redirectAttributes.addFlashAttribute("nhanVien", existingNhanVien);
            return "redirect:/admin/staff/edit/" + id;
        }
        try {
            if (!avatarFile.isEmpty()) {
                String originalFilename = StringUtils.cleanPath(avatarFile.getOriginalFilename());
                String fileExtension = FilenameUtils.getExtension(originalFilename);

                // Tạo tên file duy nhất, sử dụng UUID
                String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

                String uploadDir = "src/main/resources/static/user/img";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                if (existingNhanVien.getAvatarPath() != null) {
                    Path oldFilePath = uploadPath.resolve(existingNhanVien.getAvatarPath());
                    Files.deleteIfExists(oldFilePath);
                }
// Lưu tệp ảnh vào thư mục
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(avatarFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Cập nhật đường dẫn mới cho avatar
                existingNhanVien.setAvatarPath(uniqueFileName);
            }
        } catch (IOException e) {
            // Xử lý lỗi đọc/lưu file
            model.addAttribute("avatarError", "Lỗi tải lên ảnh đại diện.");
            return "admin/staff/edit";
        }

        List<UserRole> userRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            Role role = userService.getRoleById(roleId);
            if (role != null) {
                UserRole userRole = new UserRole(existingNhanVien, role);
                userRoles.add(userRole);
            }
        }
        ChucVu chucVu = chucVuService.getChucVuById(chucvuId);
        Optional<VanPhong> vanPhong = vanPhongService.getVanPhongById(vpId);
        VanPhong vp = vanPhong.get();
        existingNhanVien.setChucvu(chucVu);
        existingNhanVien.setVpLamViecChinh(vp);
        existingNhanVien.getUserRoles().clear(); // Xóa các role cũ
        userRoles.forEach(userRole -> existingNhanVien.getUserRoles().add(userRole)); // Thêm role mới

        userService.saveUser(existingNhanVien);

        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công");
        return "redirect:/admin/staff";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable("id") int id) {
        staffService.deleteNhanVienById(id);
        return "redirect:/admin/staff";
    }
}