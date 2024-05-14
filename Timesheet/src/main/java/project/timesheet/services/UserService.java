package project.timesheet.services;

import org.springframework.beans.factory.annotation.Autowired;
import project.timesheet.models.Role;
import project.timesheet.models.User;
import project.timesheet.repository.RoleRepository;

import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public List<Role> getAllRoles();

    public Role getRoleById(Long id);
    User findByUsername(String username);

}
