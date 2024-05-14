package project.timesheet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.models.Role;
import project.timesheet.models.User;
import project.timesheet.repository.RoleRepository;
import project.timesheet.repository.UserRepository;

import java.util.List;

@Service
public  class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}