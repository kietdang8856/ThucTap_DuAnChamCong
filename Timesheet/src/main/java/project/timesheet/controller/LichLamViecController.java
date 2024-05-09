package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.timesheet.Entity.LichLamViec;
import project.timesheet.Service.LichLamViecService;

import java.util.List;

@Controller
@RequestMapping("/lichlamviec")
public class LichLamViecController {
    @Autowired
    private LichLamViecService service;

//    @PostMapping("/lichlamviec/create")
//    public ResponseEntity<LichLamViec> create(@RequestBody LichLamViec lichlam) {
    // tim cv = id
    //tim vp lam viec == id
//    }
    @GetMapping("/index")
    public ResponseEntity<List<LichLamViec>> getAll() {
        return ResponseEntity.ok(service.getAll());
   }
}
