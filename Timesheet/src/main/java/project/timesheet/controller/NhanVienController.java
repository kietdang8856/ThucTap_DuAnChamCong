package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.timesheet.Entity.NhanVien;
import project.timesheet.Entity.NhanVienModel;
import project.timesheet.Entity.VanPhong;
import project.timesheet.Service.ChucVuService;
import project.timesheet.Service.NhanVienService;
import project.timesheet.Service.VanPhongService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienService service;
    @Autowired
    private ChucVuService chucVuService;
    @Autowired
    private VanPhongService vanPhongService;
    @GetMapping("/index")
    public ResponseEntity<List<NhanVien>> getAll(){
        return ResponseEntity.ok(service.getALL());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<NhanVien>> getOne(@PathVariable int id){
        return ResponseEntity.ok(service.getOne(id));
    }
    @PostMapping("/create")
    public ResponseEntity<NhanVien> create(@RequestBody NhanVienModel create)
    {

        NhanVien nv= new NhanVien();
        nv.setTenNV(create.getTenNV());
        nv.setEmail(create.getEmail());
        nv.setSdt(create.getSdt());
        nv.setDiaChi(create.getDiaChi());
        nv.setNgayBatDauLam(create.getNgayBatDauLam());
        nv.setGioiTinh(create.isGioiTinh());
       //setter cho chuc vu va van phong
        return ResponseEntity.ok().build();
    }
}
