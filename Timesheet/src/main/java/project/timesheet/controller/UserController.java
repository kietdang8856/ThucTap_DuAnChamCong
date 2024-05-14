package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.timesheet.models.Role;
import project.timesheet.models.User;
import project.timesheet.models.UserRole;
import project.timesheet.services.UserService;
import project.timesheet.services.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("roles") List<Long> roleIds) {
        User user = new User(username,  passwordEncoder.encode(password), true);
        List<UserRole> userRoles = new ArrayList<>();

        for (Long roleId : roleIds) {
            Role role = userService.getRoleById(roleId);
            if (role!= null) {
                UserRole userRole = new UserRole(user, role);
                userRoles.add(userRole);
            }
        }

        user.getUserRoles().clear();
        userRoles.forEach(userRole -> user.getUserRoles().add(userRole));
        userService.saveUser(user);

        return "redirect:/";
    }


    @GetMapping("/all")
    public String showUsers() {

        return "users/listusers";
    }
    @GetMapping("/edit")
    public String showUsersDetail() {

        return "users/edituser";
    }


}
