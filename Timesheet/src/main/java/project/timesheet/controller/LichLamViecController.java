package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.*;
import project.timesheet.models.LichLamViec;
import project.timesheet.models.LichLamViec;
import project.timesheet.Service.LichLamViecService;
import project.timesheet.Service.NhanVienService;
import project.timesheet.Service.TrangThaiLamViecService;
import project.timesheet.Service.VanPhongService;
import project.timesheet.models.LichLamViecModel;

import java.util.Date;
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
            LichLamViec lich = new LichLamViec();
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

    @PutMapping("/{id}")
    public ResponseEntity<LichLamViec> update(@PathVariable int id, @RequestBody LichLamViecModel update)
    {
        LichLamViec lich = service.getOne(id);
        if(lich!=null)
        {
            lich.setGioBatDau(update.getGioBatDau());
            lich.setGioKetThuc(update.getGioKetThuc());

            lich.setTenCongViec(update.getTenCongViec());
            //setter cho nhanvien, van phong va trang thai lam viec
            lich.setNhanVien(nhanVienService.getOne(update.getNhanVien_id()));
            lich.setTrangThai(trangThaiLamViecService.getOne(update.getTrangThaiLamViec_Id()));
            lich.setVpCongTac(vanPhongService.getOne(update.getVpCongTac_id()));
            service.create(lich);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id)
    {
        LichLamViec delete= service.getOne(id);
        System.out.println(java.time.LocalDate.now());

        //kiem tra ngay thuc hien delete lich lam viec say ra truoc ngay thuc hien cong viec
        //th dung
       if(java.time.LocalDate.now().isBefore(delete.getNgayLam().toLocalDate()))
       {
           service.delete(id);
           return ResponseEntity.ok().build();
       }
       //th bang
       if(java.time.LocalDate.now().isEqual(delete.getNgayLam().toLocalDate()))
       {
           //kiem tra gio thuc hien delele lich lam viec say ra truoc gio thuc hien cong viec
           if(java.time.LocalTime.now().getHour()< delete.getGioBatDau())
           {
               service.delete(id);
               return ResponseEntity.ok().build();
           }
           else
               return ResponseEntity.badRequest().build();
       }
       //th ngay thuc hien delete say ra sau ngay thuc hien cong viec
        return ResponseEntity.badRequest().build();
    }
    //-----------------------------------------------------------------------------------------------
    @PostMapping("/createLich")
    public String createMVC(Model model) {
        LichLamViec create = new LichLamViec();
        model.addAttribute("newLich",create);
        return "/lich/create";
    }
}
