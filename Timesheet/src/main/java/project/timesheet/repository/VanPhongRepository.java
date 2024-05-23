package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.timesheet.models.VanPhong;

import java.util.List;

@Repository
public interface VanPhongRepository extends JpaRepository<VanPhong, Long> {
    @Query("SELECT vp FROM VanPhong vp WHERE vp.name LIKE %:name%")
    List<VanPhong> findByNameContaining(String name);
}
