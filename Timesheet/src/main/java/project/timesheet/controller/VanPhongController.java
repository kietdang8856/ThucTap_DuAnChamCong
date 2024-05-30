package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.timesheet.models.VanPhong;
import project.timesheet.services.VanPhongService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/office")
public class VanPhongController {
    @Autowired
    private VanPhongService service;
    @GetMapping("/index")
    public ResponseEntity<List<VanPhong>> getAll(){
        return ResponseEntity.ok(service.getALL());
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<VanPhong>> getOne(@PathVariable int id){
//        return ResponseEntity.ok(Optional.ofNullable(service.getVanPhongById(id)));
//    }
    @GetMapping("/list")
    public String getAllVanPhongs(Model model) {
        List<VanPhong> vanPhongs = service.getALL();
        model.addAttribute("vanphongs", vanPhongs);
        return "vanphong/list";
    }
    @GetMapping("/search")
    public String searchVanPhong(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<VanPhong> searchResults = service.searchVanPhongByName(searchTerm);
        model.addAttribute("vanphongs", searchResults);
        return "vanphong/list"; // or whatever your HTML template name is
    }
    @GetMapping("/{id}")
    public String getVanPhongDetails(@PathVariable Integer id, Model model) {
        Optional<VanPhong> vanPhong = service.getVanPhongByid(id);
        if (vanPhong.isPresent()) {
            model.addAttribute("vanphong", vanPhong.get());
            return "vanphong";
        } else {
            return "error/404"; // or another error handling page
        }
    }
    @GetMapping("/new")
    public String createVanPhongForm(Model model) {
        model.addAttribute("vanphong", new VanPhong());
        return "vanphong/add";
    }

    @PostMapping("/new")
    public String createVanPhong(@ModelAttribute VanPhong vanPhong) {
        service.createVanPhong(vanPhong);
        return "redirect:/admin/office/list";
    }
    @GetMapping("/edit/{id}")
    public String editVanPhongForm(@PathVariable Integer id, Model model) {
        Optional<VanPhong> vanPhong = service.getVanPhongByid(id);
        if (vanPhong.isPresent()) {
            model.addAttribute("vanphong", vanPhong.get());
            return "vanphong/edit";
        } else {
            return "redirect:/admin/office/list";
        }
    }

    @PostMapping("/update/{id}")
    public String updateVanPhong(@PathVariable Integer id, @ModelAttribute VanPhong vanPhongDetails) {
        service.updateVanPhong(id, vanPhongDetails);
        return "redirect:/admin/office/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteVanPhong(@PathVariable Integer id) {
        service.deleteVanPhong(id);
        return "redirect:/admin/office/list";
    }
}
