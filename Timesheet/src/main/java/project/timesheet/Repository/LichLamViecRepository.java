package project.timesheet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.Model.LichLamViec;

@Repository
public interface LichLamViecRepository extends JpaRepository<LichLamViec,Integer> {
}
