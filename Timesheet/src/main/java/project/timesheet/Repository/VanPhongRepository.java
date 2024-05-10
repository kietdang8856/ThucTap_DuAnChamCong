package project.timesheet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.Model.VanPhong;

@Repository
public interface VanPhongRepository extends JpaRepository<VanPhong,Integer> {
}
