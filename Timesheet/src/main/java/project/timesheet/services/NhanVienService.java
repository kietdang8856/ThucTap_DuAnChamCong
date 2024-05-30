package project.timesheet.services;

import org.springframework.web.multipart.MultipartFile;
import project.timesheet.models.NhanVien;
import project.timesheet.models.Role;

import java.util.List;


public interface NhanVienService {
    public List<NhanVien> getAllStaff(Integer pageNo,
                                      Integer pageSize,
                                      String sortBy);
    public List<NhanVien> searchStaffs(String keyword);
    public NhanVien getOne(int id);

    public List<NhanVien> getAllUsers();

    public NhanVien saveUser(NhanVien user);

    public List<Role> getAllRoles();

    public Role getRoleById(Long id);

    NhanVien findByUsername(String username);

    public NhanVien findById(int id);

    public List<NhanVien> findAll();

    public void deleteUser(NhanVien user);

    boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
    Role getRoleByName(String name);
    boolean existsAdmin();
    boolean existsOtherAdmin(int userId);
}