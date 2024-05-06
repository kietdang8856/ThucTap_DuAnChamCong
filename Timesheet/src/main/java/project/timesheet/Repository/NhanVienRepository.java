package project.timesheet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.Entity.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Integer> {
}
