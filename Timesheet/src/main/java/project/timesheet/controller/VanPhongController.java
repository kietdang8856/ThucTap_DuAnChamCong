package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.timesheet.models.VanPhong;
import project.timesheet.services.VanPhongService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vanphongs")
public class VanPhongController {

    @Autowired
    private VanPhongService vanPhongService;

    @GetMapping("/search")
    public String searchVanPhong(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<VanPhong> searchResults = vanPhongService.searchVanPhongByName(searchTerm);
        model.addAttribute("vanphongs", searchResults);
        return "vanphong/list"; // or whatever your HTML template name is
    }

    @GetMapping("/vanphong/{id}")
    public String getVanPhongDetails(@PathVariable Integer id, Model model) {
        Optional<VanPhong> vanPhong = vanPhongService.getVanPhongById(id);
        if (vanPhong.isPresent()) {
            model.addAttribute("vanphong", vanPhong.get());
            model.addAttribute("nhanViens", vanPhong.get().getNhanViens());
            return "vanphong";
        } else {
            return "error/404"; // or another error handling page
        }
    }

    @GetMapping
    public String getAllVanPhongs(Model model) {
        List<VanPhong> vanPhongs = vanPhongService.getAllVanPhongs();
        model.addAttribute("vanphongs", vanPhongs);
        return "vanphong/list";
    }

    @GetMapping("/{id}")
    public String getVanPhongById(@PathVariable Integer id, Model model) {
        Optional<VanPhong> vanPhong = vanPhongService.getVanPhongById(id);
        if (vanPhong.isPresent()) {
            model.addAttribute("vanphong", vanPhong.get());
            model.addAttribute("nhanViens", vanPhong.get().getNhanViens());
        } else {
            model.addAttribute("error", "VanPhong not found");
        }
        return "vanphong/edit";
    }

    @GetMapping("/new")
    public String createVanPhongForm(Model model) {
        model.addAttribute("vanphong", new VanPhong());
        return "vanphong/add";
    }

    @PostMapping
    public String createVanPhong(@ModelAttribute VanPhong vanPhong) {
        vanPhongService.createVanPhong(vanPhong);
        return "redirect:/vanphongs";
    }

    @GetMapping("/edit/{id}")
    public String editVanPhongForm(@PathVariable Integer id, Model model) {
        Optional<VanPhong> vanPhong = vanPhongService.getVanPhongById(id);
        if (vanPhong.isPresent()) {
            model.addAttribute("vanphong", vanPhong.get());
            return "vanphong/edit";
        } else {
            return "redirect:/vanphongs";
        }
    }

    @PostMapping("/update/{id}")
    public String updateVanPhong(@PathVariable Integer id, @ModelAttribute VanPhong vanPhongDetails) {
        vanPhongService.updateVanPhong(id, vanPhongDetails);
        return "redirect:/vanphongs";
    }

    @GetMapping("/delete/{id}")
    public String deleteVanPhong(@PathVariable Integer id) {
        vanPhongService.deleteVanPhong(id);
        return "redirect:/vanphongs";
    }
}
