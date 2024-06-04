package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.timesheet.models.NhanVien;
import project.timesheet.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT CASE WHEN COUNT(ur) > 0 THEN true ELSE false END FROM UserRole ur WHERE ur.role.name = :roleName AND ur.NhanVien.id != :userId")
    boolean existsByRoleNameAndUserIdNot(String roleName, int userId);
}
