package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.timesheet.models.ChucVu;
import project.timesheet.services.*;

import java.util.List;

@Controller
@RequestMapping("/admin/position")

public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @GetMapping("/list")
    public String positions(Model model,
                            @RequestParam(defaultValue = "0") Integer pageNo,
                            @RequestParam(defaultValue = "5") Integer pageSize,
                            @RequestParam(defaultValue = "Id") String sortBy) {

        List<ChucVu> allPosition = chucVuService.getAllPosition(pageNo, pageSize, sortBy);
        model.addAttribute("allSPosition", allPosition);
        model.addAttribute("currentPage", pageNo);

        int totalPositionCount = chucVuService.getALL().size();
        int totalPages = (int) Math.ceil((double) totalPositionCount / pageSize); // Use Math.ceil
        model.addAttribute("totalPages", totalPages);

        return "position/list";
    }

    @GetMapping("/add")
    public String createPosition(Model model) {
        model.addAttribute("chucVu", new ChucVu());
        return "position/add";
    }

    @PostMapping("/add")
    public String createPosition(@ModelAttribute("chucVu") ChucVu chucVu,Model model) {
        if (chucVu.getTenChucVu() == null || chucVu.getTenChucVu().trim().isEmpty()) {
            model.addAttribute("errorMessage", "Position name cannot be empty.");
            return "position/add"; // Re-render the form with the error message
        }
        chucVuService.create(chucVu);
        return "redirect:/admin/position/list";
    }

    @GetMapping("/edit/{id}")
    public String editPosition(@PathVariable("id") Integer id, Model model) {
        ChucVu chucVu = chucVuService.getChucVuById(id);
        if (chucVu != null) {
            model.addAttribute("chucVu", chucVu);
            return "position/edit";
        } else {
            return "redirect:/admin/position/list";
        }
    }

    @PostMapping("/edit")
    public String updatePosition(@ModelAttribute("chucVu") ChucVu chucVu, Model model) {
        if (chucVu.getTenChucVu() == null || chucVu.getTenChucVu().trim().isEmpty()) {
            model.addAttribute("errorMessage", "Position name cannot be empty.");
            return "position/edit"; // Re-render the form with the error message
        }
        chucVuService.create(chucVu);
        return "redirect:/admin/position/list";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String deletePosition(@PathVariable("id") Integer id) {
        chucVuService.delete(id);
        return "redirect:/admin/position/list";
    }

}