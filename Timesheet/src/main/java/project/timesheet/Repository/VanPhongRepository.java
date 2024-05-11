package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.models.VanPhong;

@Repository
public interface VanPhongRepository extends JpaRepository<VanPhong,Integer> {
}
