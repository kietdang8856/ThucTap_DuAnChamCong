package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.timesheet.models.LichLamViecModel;
import project.timesheet.services.TrangThaiLamViecService;
import project.timesheet.services.VanPhongService;

@Controller
public class HomeController {
    @Autowired
    private VanPhongService vanPhongService;
    @Autowired
    private TrangThaiLamViecService trangThaiLamViecService;
    @GetMapping("/")
    public String showHome(Model model) {
        LichLamViecModel lich = new LichLamViecModel();
        model.addAttribute("lichlam",lich);
        model.addAttribute("vanPhongList",vanPhongService.getALL());
        model.addAttribute("trangThaiList",trangThaiLamViecService.getALL());
        return "home";
    }
    @GetMapping("/login")
    public String Login() {

        return "users/login";
    }
//    private final UserService userService;
//
//    public HomeController() {
//        this.userService = userService;
//    }

//    @GetMapping("/")
//    public String showHome(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
//        model.addAttribute("user", userService.getUserById(currentUser.getId()));
//        return "home";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
//        if (currentUser != null) {
//            return "redirect:/";
//        }
//        return "users/login";
//    }
//
//    @GetMapping("/access-denied")
//    public String showAccessDeniedPage() {
//        return "access-denied";
//    }


}
