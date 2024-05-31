package project.timesheet.controller;

import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.timesheet.models.LichLamViec;
import project.timesheet.models.LichLamViecModel;
import project.timesheet.models.NhanVien;
import project.timesheet.services.LichLamViecService;
import project.timesheet.services.NhanVienService;
import project.timesheet.services.TrangThaiLamViecService;
import project.timesheet.services.VanPhongService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class    HomeController {
    @Autowired
    private LichLamViecService service;
    @Autowired
    private VanPhongService vanPhongService;
    @Autowired
    private TrangThaiLamViecService trangThaiLamViecService;
    @Autowired
    private NhanVienService nhanVienService;
    @GetMapping("/")
    public String showHome(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            NhanVien currentUser = nhanVienService.findByUsername(username);

            if (currentUser != null) {
                session.setAttribute("currentUser", currentUser); // Truyền thông tin người dùng
            }
        }
        LichLamViecModel lich = new LichLamViecModel();
        List<LichLamViec> listLich = service.getAll();
        model.addAttribute("listLichLam",listLich);
        model.addAttribute("lichlam",lich);
        model.addAttribute("vanPhongList",vanPhongService.getALL());
        model.addAttribute("trangThaiList",trangThaiLamViecService.getALL());
        return "home";
    }
    @GetMapping("/{id}")
    public String showDetails(@PathVariable int id, Model model) {
        NhanVien user = nhanVienService.findById(id);
        model.addAttribute("user", user);
        return "users/show";
    }
    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        return "profile";
    }
    @PostMapping("/profile/update/{id}")
    public String updateProfile(
            // ... (các tham số giống như phương thức editUser trong UserController)
            @PathVariable int id,
            @RequestParam("tenNV") String tenNV,
            @RequestParam("sdt") String sdt,
            @RequestParam("diaChi") String diaChi,
            @RequestParam("avatar") MultipartFile avatarFile,
            @RequestParam("gioiTinh") int gioiTinh,
            Model model,
            HttpSession session
    ) throws IOException {
        // ... (logic cập nhật thông tin người dùng tương tự như trong editUser)
        NhanVien existingNhanVien = nhanVienService.findById(id);
        if (existingNhanVien == null) {
            model.addAttribute("error", "User not found with ID: " + id);
            return "redirect:/login";
        }
        existingNhanVien.setTenNV(tenNV);
        existingNhanVien.setSdt(sdt);
        existingNhanVien.setDiaChi(diaChi);        // Cập nhật session
        existingNhanVien.setGioiTinh(gioiTinh);
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
            return "/profile";
        }
        nhanVienService.saveUser(existingNhanVien);
        session.setAttribute("currentUser", existingNhanVien);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String Login() {

        return "users/login";
    }



}
