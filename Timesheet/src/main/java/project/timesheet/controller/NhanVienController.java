package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.timesheet.models.ChucVu;
import project.timesheet.models.NhanVien;
import project.timesheet.models.NhanVienModel;
import project.timesheet.models.VanPhong;
import project.timesheet.Service.ChucVuService;
import project.timesheet.Service.NhanVienService;
import project.timesheet.Service.VanPhongService;

import java.util.List;

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
    public ResponseEntity<NhanVien> getOne(@PathVariable int id){
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
        VanPhong vp = vanPhongService.getOne(create.getVpLamViecChinh_id());
        ChucVu cv = chucVuService.getOne(create.getChucVu_id());
        nv.setVpLamViecChinh(vp);
        nv.setChucvu(cv);
        service.create(nv);
        return ResponseEntity.ok().build();
    }
}