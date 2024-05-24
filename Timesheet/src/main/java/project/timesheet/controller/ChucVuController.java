package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.timesheet.models.ChucVu;
import project.timesheet.models.NhanVien;
import project.timesheet.services.*;

import java.util.List;

@Controller
@RequestMapping("/admin/position")

public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @GetMapping
    public String positions( Model model,
                          @RequestParam(defaultValue = "0") Integer pageNo,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "Id") String sortBy){
        List<ChucVu> allPosition = chucVuService.getAllPosition(pageNo, pageSize, sortBy);
        model.addAttribute("allSPosition", allPosition);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", chucVuService.getAll().size() / pageSize);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "admin/position/list";
    }

    @GetMapping("/add")
    public String createPosition(Model model) {
        model.addAttribute("chucVu", new ChucVu());
        return "admin/position/add";
    }

    @PostMapping("/add")
    public String createPosition(@ModelAttribute("chucVu") ChucVu chucVu) {
        chucVuService.savePosition(chucVu);
        return "redirect:/admin/position";
    }

    @GetMapping("/edit/{id}")
    public String editPosition(@PathVariable("id") Integer id, Model model) {
        ChucVu chucVu = chucVuService.getPositionById(id);
        if (chucVu != null) {
            model.addAttribute("chucVu", chucVu);
            return "admin/position/edit";
        } else {
            return "redirect:/admin/position";
        }
    }

    @PostMapping("/edit")
    public String updatePosition(@ModelAttribute("chucVu") ChucVu chucVu) {
        chucVuService.savePosition(chucVu);
        return "redirect:/admin/position";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String deletePosition(@PathVariable("id") Integer id) {
        chucVuService.deletePositionById(id);
        return "redirect:/admin/position";
    }

    @GetMapping("/search")
    public String searchPosition(Model model,
                                 @RequestParam String keyword,
                                 @RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 @RequestParam(defaultValue = "Id") String sortBy) {
        List<ChucVu> searchPosition = chucVuService.searchPosition(keyword);
        model.addAttribute("allSPosition", searchPosition); // Correct the attribute name
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", searchPosition.size() / pageSize);
        return "admin/position/list";
    }
}