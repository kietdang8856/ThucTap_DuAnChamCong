    package project.timesheet.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import project.timesheet.models.*;
    import project.timesheet.services.LichLamViecService;
    import project.timesheet.services.NhanVienService;
    import project.timesheet.services.TrangThaiLamViecService;
    import project.timesheet.services.VanPhongService;

    import java.util.ArrayList;
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

    //    @PostMapping("/create")
    //    public ResponseEntity<LichLamViec> create(@RequestBody LichLamViecModel lichlam) {
    //        LichLamViec lich = new LichLamViec();
    //        lich.setGioBatDau(lichlam.getGioBatDau());
    //        lich.setGioKetThuc(lichlam.getGioKetThuc());
    //        lich.setNgayLam(lichlam.getNgayLam());
    //        lich.setTenCongViec(lichlam.getTenCongViec());
    //        //setter cho nhanvien, van phong va trang thai lam viec
    //        lich.setNhanVien(nhanVienService.getOne(lichlam.getNhanVien_id()));
    //        lich.setTrangThai(trangThaiLamViecService.getOne(lichlam.getTrangThaiLamViec_Id()));
    //        lich.setVpCongTac(vanPhongService.getOne(lichlam.getVpCongTac_id()));
    //        service.create(lich);
    //        return ResponseEntity.ok().build();
    //    }

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
                lich.setVpCongTac(vanPhongService.getVanPhongById(update.getVpCongTac_id()));
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
//                if(java.time.LocalTime.now().getHour()< delete.getGioBatDau())
//                {
//                    service.delete(id);
//                    return ResponseEntity.ok().build();
//                }
//                else
//                    return ResponseEntity.badRequest().build();
            }
            //th ngay thuc hien delete say ra sau ngay thuc hien cong viec
            return ResponseEntity.badRequest().build();
        }
        //-----------------------------------------------------------------------------------------------
        @RequestMapping("/getAll")
        public String getAll(Model model)
        {
            List<LichLamViec> list = service.getAll();
            model.addAttribute("listLichLam",list);
            return "lich/index";
        }
        @RequestMapping("/taolich")
        public String createMVC(Model model) {

            LichLamViecModel lich = new LichLamViecModel();
            model.addAttribute("lichlam",lich);
            model.addAttribute("vanPhongList",vanPhongService.getALL());
            model.addAttribute("trangThaiList",trangThaiLamViecService.getALL());
            return "lich/todo";
        }
        @PostMapping("/save")
        public void submitForm(@ModelAttribute("lichlam") LichLamViecModel lichlam) {
            LichLamViec lich = new LichLamViec();
            lich.setGioBatDau(lichlam.getGioBatDau());
            lich.setGioKetThuc(lichlam.getGioKetThuc());
            lich.setNgayLam(lichlam.getNgayLam());
            lich.setTenCongViec(lichlam.getTenCongViec());
            //setter cho nhanvien, van phong va trang thai lam viec

            lich.setNhanVien(nhanVienService.findByUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
            lich.setTrangThai(trangThaiLamViecService.getOne(lichlam.getTrangThaiLamViec_Id()));
            lich.setVpCongTac(vanPhongService.getVanPhongById(lichlam.getVpCongTac_id()));
            service.create(lich);
        }
        @GetMapping("/getbydate")
        public ResponseEntity<List<LichLamViec>> getFromDateToDate(@RequestBody LichLamViecRequest lichlam) {
            return ResponseEntity.ok(service.getAllBetweenDate(lichlam.getTuNgay(),lichlam.getDenNgay()));
        }

        @GetMapping("/getbyvp/{id}")
        public ResponseEntity<List<LichLamViec>> getAllByVanPhong(@PathVariable("id") int id) {
            return ResponseEntity.ok(service.getAllByVanPhongId(id));
        }
        @GetMapping("/getbynv/{id}")
        public ResponseEntity<List<LichLamViec>> getAllByNhanVien(@PathVariable("id") int id) {
            return ResponseEntity.ok(service.getAllByNhanVienId(id));
        }
        @GetMapping("/getbytt/{id}")
        public ResponseEntity<List<LichLamViec>> getAllByTrangThai(@PathVariable("id") int id) {
            return ResponseEntity.ok(service.getAllByTrangThaiId(id));
        }
//        @RequestMapping("/searchpage")
//        public String searchPage(Model model)
//        {
//            model.addAttribute("vanPhongList",vanPhongService.getALL());
//            model.addAttribute("trangThaiList",trangThaiLamViecService.getALL());
//            model.addAttribute("listNhanVien",nhanVienService.getAllUsers());
//            model.addAttribute("searchFilter",new LichLamViecSearchFilter());
//            return "search";
//        }
        @GetMapping("/search")
        public String search(@ModelAttribute("searchFilter") LichLamViecSearchFilter filter,Model model) {
            List<LichLamViec> filter1;
            if (filter.getTuNgay() != null && filter.getDenNgay() != null) {
                 filter1 = service.getAllBetweenDate(filter.getTuNgay(), filter.getDenNgay());
            }
            else filter1 = service.getAll();
//            if(filter.getTuNgay()!=null && filter.getDenNgay()!=null)
//            {
//                filter1.removeIf(lich -> (lich.getNgayLam().after(filter.getDenNgay())));
//                filter1.removeIf(lich -> (lich.getNgayLam().before(filter.getTuNgay())));
//            }
            if(filter.getTrangThaiLamViec_Id()!=null)
            {
                filter1.removeIf(lich -> lich.getTrangThai().getId() != filter.getTrangThaiLamViec_Id());
            }

            if(filter.getTenNV()!=null) {
                filter1.removeIf(lich -> !lich.getNhanVien().getTenNV().equalsIgnoreCase( filter.getTenNV()));
            }

            if(filter.getVpCongTac_id()!=null) {
                filter1.removeIf(lich -> lich.getVpCongTac().getId() != filter.getVpCongTac_id());
            }
            model.addAttribute("listLichLam",filter1);
            model.addAttribute("vanPhongList",vanPhongService.getALL());
            model.addAttribute("trangThaiList",trangThaiLamViecService.getALL());
            model.addAttribute("listNhanVien",nhanVienService.getAllUsers());
            model.addAttribute("searchFilter",new LichLamViecSearchFilter());
            return "result";
        }

    }
