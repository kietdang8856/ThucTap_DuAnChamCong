package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.timesheet.Entity.NhanVien;
import project.timesheet.Entity.VanPhong;
import project.timesheet.Service.NhanVienService;
import project.timesheet.Service.VanPhongService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienService service;
    @GetMapping("/index")
    public ResponseEntity<List<NhanVien>> getAll(){
        return ResponseEntity.ok(service.getALL());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<NhanVien>> getOne(@PathVariable int id){
        return ResponseEntity.ok(service.getOne(id));
    }
}
