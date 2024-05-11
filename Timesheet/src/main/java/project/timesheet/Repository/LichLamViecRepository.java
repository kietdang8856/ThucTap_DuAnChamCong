package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.models.LichLamViec;

@Repository
public interface LichLamViecRepository extends JpaRepository<LichLamViec,Integer> {
}
