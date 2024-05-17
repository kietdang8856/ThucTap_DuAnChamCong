package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.timesheet.execption.ResourceNotFoundException;
import project.timesheet.models.Role;
import project.timesheet.models.User;
import project.timesheet.models.UserRole;
import project.timesheet.repository.UserRepository;
import project.timesheet.services.UserService;
import project.timesheet.services.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
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
                               @RequestParam("roles") List<Long> roleIds)
    {
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


    @GetMapping("/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/show";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        List<Role> roles = userService.getAllRoles();
        List<Role> userRoles = user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toList());
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", userRoles);
        return "users/edituser";
    }

        @PostMapping("/{id}/edit")
        public String editUser(@PathVariable Long id,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("roles") List<Long> roleIds,
                               Model model) {
            User existingUser = userService.findById(id);
            if (existingUser != null) {
                existingUser.setUsername(username);
                if (!password.isEmpty()) {
                    existingUser.setPassword(passwordEncoder.encode(password));
                }
                existingUser.setEnabled(true);
                existingUser.getUserRoles().clear();
                List<UserRole> userRoles = new ArrayList<>();
                for (Long roleId : roleIds) {
                    Role role = userService.getRoleById(roleId);
                    if (role != null) {
                        UserRole userRole = new UserRole(existingUser, role);
                        userRoles.add(userRole);
                    }
                }
                userRoles.forEach(userRole -> existingUser.getUserRoles().add(userRole));
                userService.saveUser(existingUser);
                model.addAttribute("message", "User updated successfully");
            } else {
                model.addAttribute("error", "User not found with ID: " + id);
            }
            return "redirect:/users/list";
        }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        model.addAttribute("message", "User deleted successfully");
        return "redirect:/users";
    }

    @GetMapping("/list")
    public String showAll(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/list";
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
