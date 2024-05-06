package project.timesheet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
