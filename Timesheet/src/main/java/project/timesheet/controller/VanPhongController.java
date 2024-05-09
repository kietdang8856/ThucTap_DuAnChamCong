package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.timesheet.Entity.VanPhong;
import project.timesheet.Service.VanPhongService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vanphong")
public class VanPhongController {
    @Autowired
    private VanPhongService service;
    @GetMapping("/index")
    public ResponseEntity<List<VanPhong>> getAll(){
        return ResponseEntity.ok(service.getALL());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<VanPhong>> getOne(@PathVariable int id){
        return ResponseEntity.ok(Optional.ofNullable(service.getOne(id)));
    }
}
