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

    import java.time.LocalDateTime;
    import java.time.ZoneId;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;
    import java.util.Objects;

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

//        @GetMapping("/index")
//        public ResponseEntity<List<LichLamViec>> getAll() {
//            return ResponseEntity.ok(service.getAll());
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<LichLamViec> getOne(@PathVariable int id) {
//            return ResponseEntity.ok(service.getOne(id));
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<LichLamViec> update(@PathVariable int id, @RequestBody LichLamViecModel update)
//        {
//            LichLamViec lich = service.getOne(id);
//            if(lich!=null)
//            {
//                lich.setGioBatDau(update.getGioBatDau());
//                lich.setGioKetThuc(update.getGioKetThuc());
//                lich.setTenCongViec(update.getTenCongViec());
//                //setter cho nhanvien, van phong va trang thai lam viec
//                lich.setNhanVien(nhanVienService.getOne(update.getNhanVien_id()));
//                lich.setTrangThai(trangThaiLamViecService.getOne(update.getTrangThaiLamViec_Id()));
//                lich.setVpCongTac(vanPhongService.getVanPhongById(update.getVpCongTac_id()));
//                service.create(lich);
//                return ResponseEntity.ok().build();
//            }
//            else
//                return ResponseEntity.notFound().build();
//        }
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> delete(@PathVariable int id)
//        {
//            LichLamViec delete= service.getOne(id);
//            System.out.println(java.time.LocalDate.now());
//
//            //kiem tra ngay thuc hien delete lich lam viec say ra truoc ngay thuc hien cong viec
//            //th dung
//            if(java.time.LocalDate.now().isBefore(delete.getNgayLam().toLocalDate()))
//            {
//                service.delete(id);
//                return ResponseEntity.ok().build();
//            }
//            //th bang
//            if(java.time.LocalDate.now().isEqual(delete.getNgayLam().toLocalDate()))
//            {
//                //kiem tra gio thuc hien delele lich lam viec say ra truoc gio thuc hien cong viec
////                if(java.time.LocalTime.now().getHour()< delete.getGioBatDau())
////                {
////                    service.delete(id);
////                    return ResponseEntity.ok().build();
////                }
////                else
////                    return ResponseEntity.badRequest().build();
//            }
//            //th ngay thuc hien delete say ra sau ngay thuc hien cong viec
//            return ResponseEntity.badRequest().build();
//        }
        //-----------------------------------------------------------------------------------------------
//        @RequestMapping("/getAll")
//        public String getAll(Model model)
//        {
//            List<LichLamViec> list = service.getAll();
//            model.addAttribute("listLichLam",list);
//            return "lich/index";
//        }
        @RequestMapping("/taolich")
        public String createMVC(Model model) {

            LichLamViecModel lich = new LichLamViecModel();
            model.addAttribute("lichlam",lich);
            model.addAttribute("vanPhongList",vanPhongService.getALL());
            model.addAttribute("trangThaiList",trangThaiLamViecService.getALL());
            return "lich/todo";
        }
        @PostMapping("/save")
        public String submitForm(@ModelAttribute("lichlam") LichLamViecModel lichlam) {
            LichLamViec lich = new LichLamViec();
            lich.setGioBatDau(lichlam.getGioBatDau());
            lich.setGioKetThuc(lichlam.getGioKetThuc());
            lich.setNgayLam(lichlam.getNgayLam());
            lich.setTenCongViec(lichlam.getTenCongViec());

            // Lấy thời gian hiện tại từ server
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));

            // Chuyển đổi ngày và giờ bắt đầu từ lichlam thành LocalDateTime
            LocalDateTime lichDateTime = LocalDateTime.of(lichlam.getNgayLam().toLocalDate(), lichlam.getGioBatDau());

            if (lichDateTime.isBefore(now)) {
                // Nếu thời gian tạo lịch sớm hơn thời gian hiện tại, thông báo lỗi
                // (Sử dụng flash attribute hoặc cách khác để hiển thị thông báo lỗi trên trang)
                return "dont do that";
            } else {
                //setter cho nhanvien, van phong va trang thai lam viec
                lich.setNhanVien(nhanVienService.findByUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
                lich.setTrangThai(trangThaiLamViecService.getOne(lichlam.getTrangThaiLamViec_Id()));
                lich.setVpCongTac(vanPhongService.getVanPhongById(lichlam.getVpCongTac_id()));
                service.create(lich);
                return "redirect:/";
            }
        }
//        @GetMapping("/getbydate")
//        public ResponseEntity<List<LichLamViec>> getFromDateToDate(@RequestBody LichLamViecRequest lichlam) {
//            return ResponseEntity.ok(service.getAllBetweenDate(lichlam.getTuNgay(),lichlam.getDenNgay()));
//        }
//
//        @GetMapping("/getbyvp/{id}")
//        public ResponseEntity<List<LichLamViec>> getAllByVanPhong(@PathVariable("id") int id) {
//            return ResponseEntity.ok(service.getAllByVanPhongId(id));
//        }
//        @GetMapping("/getbynv/{id}")
//        public ResponseEntity<List<LichLamViec>> getAllByNhanVien(@PathVariable("id") int id) {
//            return ResponseEntity.ok(service.getAllByNhanVienId(id));
//        }
//        @GetMapping("/getbytt/{id}")
//        public ResponseEntity<List<LichLamViec>> getAllByTrangThai(@PathVariable("id") int id) {
//            return ResponseEntity.ok(service.getAllByTrangThaiId(id));
//        }
       @RequestMapping("/search")
       public String searchPage(Model model)
       {
           LichLamViecModel lichlam = new LichLamViecModel();
           List<LichLamViec> list = new ArrayList<>();
           model.addAttribute("lichlam",lichlam);
           model.addAttribute("listLichLam",list);
          model.addAttribute("vanPhongList",vanPhongService.getALL());
           model.addAttribute("trangThaiList",trangThaiLamViecService.getALL());
           model.addAttribute("listNhanVien",nhanVienService.getAllUsers());
          model.addAttribute("searchFilter",new LichLamViecSearchFilter());
           return "result";
       }
        @PostMapping("/search")
        public String search(@ModelAttribute("searchFilter") LichLamViecSearchFilter filter,Model model) {

                List<LichLamViec> filter1;
                if (filter.getTuNgay() != null && filter.getDenNgay() != null) {
                    filter1 = service.getAllBetweenDate(filter.getTuNgay(), filter.getDenNgay());
                } else filter1 = service.getAll();
//            if(filter.getTuNgay()!=null && filter.getDenNgay()!=null)
//            {
//                filter1.removeIf(lich -> (lich.getNgayLam().after(filter.getDenNgay())));
//                filter1.removeIf(lich -> (lich.getNgayLam().before(filter.getTuNgay())));
//            }
                if (filter.getTrangThaiLamViec_Id() != null) {
                    filter1.removeIf(lich -> lich.getTrangThai().getId() != filter.getTrangThaiLamViec_Id());
                }

//            if (filter.getTenNV() != null && !filter.getTenNV().isEmpty()) {
//                String searchName = filter.getTenNV().toLowerCase(); // Chuyển về chữ thường
//                filter1.removeIf(lich -> !lich.getNhanVien().getTenNV().toLowerCase().contains(searchName)); // So sánh sau khi chuyển về chữ thường
//            }


                if (filter.getVpCongTac_id() != null) {
                    filter1.removeIf(lich -> lich.getVpCongTac().getId() != filter.getVpCongTac_id());
                }
            // Cải tiến tìm kiếm tên nhân viên
            if (filter.getTenNV() != null && !filter.getTenNV().isEmpty()) {
                String[] searchTerms = filter.getTenNV().toLowerCase().split("\\s+"); // Tách thành các từ riêng biệt
                filter1.removeIf(lich -> {
                    String tenNV = lich.getNhanVien().getTenNV().toLowerCase();
                    for (String term : searchTerms) {
                        if (!tenNV.contains(term)) {
                            return true; // Loại bỏ nếu có bất kỳ từ nào không khớp
                        }
                    }
                    return false; // Giữ lại nếu tất cả các từ đều khớp
                });
            }
            LichLamViecModel lichlam = new LichLamViecModel();
            model.addAttribute("lichlam",lichlam);
                model.addAttribute("listLichLam", filter1);
                model.addAttribute("vanPhongList", vanPhongService.getALL());
                model.addAttribute("trangThaiList", trangThaiLamViecService.getALL());
                model.addAttribute("listNhanVien", nhanVienService.getAllUsers());
                model.addAttribute("searchFilter", filter);
                return "result";
            }
        @RequestMapping("/update/{id}")
        public String update(@ModelAttribute("lichlam") LichLamViecModel update,@PathVariable("id") int id,Model model) {
            LichLamViec lich=service.getOne(id);
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusMinutes(1);
            boolean hasError = false; // Biến đánh dấu có lỗi hay không
            // Chuyển đổi ngày và giờ bắt đầu của lịch làm việc thành LocalDateTime
            LocalDateTime lichDateTime = LocalDateTime.of(lich.getNgayLam().toLocalDate(), lich.getGioBatDau());
            if (update.getGioBatDau().isAfter(update.getGioKetThuc()) || update.getGioKetThuc().isBefore(update.getGioBatDau())) {
                hasError = true; // Đánh dấu có lỗi
            }
            if (update.getTenCongViec().trim().isEmpty()) {
                model.addAttribute("sweetAlertType", "error");
                model.addAttribute("sweetAlertTitle", "Lỗi");
                model.addAttribute("sweetAlertText", "Tên công việc không được để trống.");
                hasError = true;
            }
            if (lichDateTime.isAfter(now))//th ngay lam cua lich > ngay hien tai
            {
                if (hasError) {
                    // Hiển thị SweetAlert2 nếu có lỗi
                    model.addAttribute("sweetAlertType", "error");
                    model.addAttribute("sweetAlertTitle", "Lỗi");
                    model.addAttribute("sweetAlertText", "Giờ bắt đầu phải nhỏ hơn giờ kết thúc.");
                    return "redirect:/";
                } else {
                lich.setGioBatDau(update.getGioBatDau());
                lich.setGioKetThuc(update.getGioKetThuc());
                lich.setNgayLam(update.getNgayLam());
                lich.setTenCongViec(update.getTenCongViec());
                //setter cho nhanvien, van phong va trang thai lam viec

                lich.setTrangThai(trangThaiLamViecService.getOne(update.getTrangThaiLamViec_Id()));
                lich.setVpCongTac(vanPhongService.getVanPhongById(update.getVpCongTac_id()));
                service.update(lich);}
            }
            return "redirect:/";
        }
        @RequestMapping("/delete/{id}")
        public String delete(@PathVariable("id") int id) {
            LichLamViec lich = service.getOne(id);
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusMinutes(1);

            // Chuyển đổi ngày và giờ bắt đầu của lịch làm việc thành LocalDateTime
            LocalDateTime lichDateTime = LocalDateTime.of(lich.getNgayLam().toLocalDate(), lich.getGioBatDau());
            if (lichDateTime.isAfter(now))//th ngay lam cua lich > ngay hien tai
            {
                if (lich != null)
                    service.delete(id);
            }
            return "redirect:/";
        }
//        @PutMapping("/update/{id}")
//        public String update(@ModelAttribute("lichlam") LichLamViecModel update,@PathVariable("id")int id) {
//            LichLamViec lich=service.getOne(id);
//            lich.setGioBatDau(update.getGioBatDau());
//            lich.setGioKetThuc(update.getGioKetThuc());
//            lich.setNgayLam(update.getNgayLam());
//            lich.setTenCongViec(update.getTenCongViec());
//            //setter cho nhanvien, van phong va trang thai lam viec
//            lich.setNhanVien(nhanVienService.findByUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
//            lich.setTrangThai(trangThaiLamViecService.getOne(update.getTrangThaiLamViec_Id()));
//            lich.setVpCongTac(vanPhongService.getVanPhongById(update.getVpCongTac_id()));
//            service.update(lich);
//            return "result";
//
//        }
//        @GetMapping("/searchapi")
//        public ResponseEntity<List<LichLamViec>> searchAPI(@RequestBody LichLamViecSearchFilter filter) {
//
//            List<LichLamViec> filter1;
//            if (filter.getTuNgay() != null && filter.getDenNgay() != null) {
//                filter1 = service.getAllBetweenDate(filter.getTuNgay(), filter.getDenNgay());
//            } else filter1 = service.getAll();
//            if (filter.getTenNV() != "" || filter.getTenNV() !=null) {
//               filter1.removeIf(lich -> !lich.getNhanVien().getTenNV().contains(filter.getTenNV()));
//            }
////            if(filter.getTuNgay()!=null && filter.getDenNgay()!=null)
////            {
////                filter1.removeIf(lich -> (lich.getNgayLam().after(filter.getDenNgay())));
////                filter1.removeIf(lich -> (lich.getNgayLam().before(filter.getTuNgay())));
////            }
//            if (filter.getTrangThaiLamViec_Id() != null) {
//                filter1.removeIf(lich -> lich.getTrangThai().getId() != filter.getTrangThaiLamViec_Id());
//            }
//
////            if (filter.getTenNV() != null) {
////                filter1.removeIf(lich -> !(lich.getNhanVien().getTenNV().equals(filter.getTenNV())));
////            }
//
//            if (filter.getVpCongTac_id() != null) {
//                filter1.removeIf(lich -> lich.getVpCongTac().getId() != filter.getVpCongTac_id());
//            }
////            model.addAttribute("listLichLam", filter1);
////            model.addAttribute("vanPhongList", vanPhongService.getALL());
////            model.addAttribute("trangThaiList", trangThaiLamViecService.getALL());
////            model.addAttribute("listNhanVien", nhanVienService.getAllUsers());
////            model.addAttribute("searchFilter", filter);
//            return ResponseEntity.ok(filter1);
//        }
    }
