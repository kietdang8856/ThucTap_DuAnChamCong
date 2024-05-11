package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.models.TrangThaiLamViec;

@Repository
public interface TrangThaiLamViecRepository extends JpaRepository<TrangThaiLamViec,Integer> {
}
