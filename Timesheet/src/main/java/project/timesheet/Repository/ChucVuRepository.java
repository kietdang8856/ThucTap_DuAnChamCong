package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.models.ChucVu;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu,Integer> {
}
