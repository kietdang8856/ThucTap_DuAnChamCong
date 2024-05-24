package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.timesheet.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT CASE WHEN COUNT(ur) > 0 THEN true ELSE false END FROM UserRole ur WHERE ur.role.name = :roleName AND ur.nhanVien.id != :userId")
    boolean existsByRoleNameAndUserIdNot(String roleName, int userId);

    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.nhanVien.id = :nhanVienId")
    void deleteByNhanVienId(@Param("nhanVienId") int nhanVienId);
}