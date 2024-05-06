package project.timesheet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
