package project.timesheet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.Model.TrangThaiLamViec;

@Repository
public interface TrangThaiLamViecRepository extends JpaRepository<TrangThaiLamViec,Integer> {
}
