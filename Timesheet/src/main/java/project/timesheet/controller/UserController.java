package project.timesheet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/login")
    public String Login() {

        return "users/login";
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
