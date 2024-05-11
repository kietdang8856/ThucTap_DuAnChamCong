package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.timesheet.models.TrangThaiLamViec;
import project.timesheet.services.TrangThaiLamViecService;

import java.util.List;

@Controller
@RequestMapping("/ttlamviec")
public class TrangThaiLamViecController {
    @Autowired
    private TrangThaiLamViecService service;
    @GetMapping("/index")
    public ResponseEntity<List<TrangThaiLamViec>> getAll(){
        return ResponseEntity.ok(service.getALL());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TrangThaiLamViec> getOne(@PathVariable int id){
        return ResponseEntity.ok(service.getOne(id));
    }
}
