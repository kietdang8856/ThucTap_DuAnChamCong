package project.timesheet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.Entity.ChucVu;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu,Integer> {
}
