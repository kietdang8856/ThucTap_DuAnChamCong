package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.models.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Integer> {
}
