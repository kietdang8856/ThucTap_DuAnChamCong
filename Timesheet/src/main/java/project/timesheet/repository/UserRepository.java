package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.timesheet.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}