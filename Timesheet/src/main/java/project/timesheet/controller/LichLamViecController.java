package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.timesheet.Entity.LichLamViec;
import project.timesheet.Entity.LichLamViecModel;
import project.timesheet.Service.LichLamViecService;
import project.timesheet.Service.NhanVienService;
import project.timesheet.Service.TrangThaiLamViecService;
import project.timesheet.Service.VanPhongService;

import java.util.List;

@Controller
@RequestMapping("/lichlamviec")
public class LichLamViecController {
    @Autowired
    private LichLamViecService service;
    @Autowired
    private TrangThaiLamViecService trangThaiLamViecService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private VanPhongService vanPhongService;

 @PostMapping("/create")
  public ResponseEntity<LichLamViec> create(@RequestBody LichLamViecModel lichlam) {
     LichLamViec lich= new LichLamViec();
     lich.setGioBatDau(lichlam.getGioBatDau());
     lich.setGioKetThuc(lichlam.getGioKetThuc());
     lich.setNgayLam(lichlam.getNgayLam());
     lich.setTenCongViec(lichlam.getTenCongViec());
     //setter cho nhanvien, van phong va trang thai lam viec
     lich.setNhanVien(nhanVienService.getOne(lichlam.getNhanVien_id()));
     lich.setTrangThai(trangThaiLamViecService.getOne(lichlam.getTrangThaiLamViec_Id()));
     lich.setVpCongTac(vanPhongService.getOne(lichlam.getVpCongTac_id()));
     service.create(lich);
     return ResponseEntity.ok().build();
}
    @GetMapping("/index")
    public ResponseEntity<List<LichLamViec>> getAll() {
        return ResponseEntity.ok(service.getAll());
   }
    @GetMapping("/{id}")
    public ResponseEntity<LichLamViec> getOne(@PathVariable int id) {
        return ResponseEntity.ok(service.getOne(id));
    }
}
