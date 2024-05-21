package project.timesheet.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.models.NhanVien;
import project.timesheet.models.UserRole;
import project.timesheet.repository.StaffRepository;
import project.timesheet.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StaffService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private StaffRepository staffRepository;

    public List<NhanVien> getAllStaff() {
        return staffRepository.findAll();
    }

    public List<NhanVien> getAllStaff(Integer pageNo,
                                 Integer pageSize,
                                 String sortBy){
        return staffRepository.getAllStaff(pageNo, pageSize, sortBy);
    }

    public NhanVien getStaffById(int id) {
        return staffRepository.findById(id).orElse(null);
    }

    public NhanVien saveStaff(NhanVien nhanVien) {
        return staffRepository.save(nhanVien);
    }

    @Transactional
    public void deleteStaffAndAssociatedUserRoles(int id) {
        // Xóa tất cả các user_role liên quan đến NhanVien có ID là id
        userRoleRepository.deleteByNhanVienId(id);

        // Xóa NhanVien có ID là id
        staffRepository.deleteById(id);
    }
}
