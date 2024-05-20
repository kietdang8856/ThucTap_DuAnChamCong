package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.timesheet.models.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Integer> {
    NhanVien findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    //lệnh này dùng để tìm role name đã tồn tại trong tài khoản nào chưa
    @Query("SELECT CASE WHEN COUNT(ur) > 0 THEN true ELSE false END FROM UserRole ur WHERE ur.role.name = :roleName")
    boolean existsByRoleName(String roleName);

    //lệnh này dùng để xóa quan hệ giữa userrole và nhân viên
    @Modifying // Sử dụng @Modifying để đánh dấu đây là một truy vấn thay đổi dữ liệu
    @Query("DELETE FROM UserRole ur WHERE ur.NhanVien = :user") // Truy vấn JPQL để xóa UserRole
    void deleteUserRoleByNhanVien(NhanVien user);
}
