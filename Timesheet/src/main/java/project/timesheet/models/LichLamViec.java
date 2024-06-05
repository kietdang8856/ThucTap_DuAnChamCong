    package project.timesheet.models;

    import jakarta.annotation.Nullable;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.sql.Date;
    import java.time.LocalTime;

    @Data
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "LichLamViec")
    public class LichLamViec {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @ManyToOne
        @Nullable
        private VanPhong vpCongTac;
        @ManyToOne
        private TrangThaiLamViec trangThai;
        @ManyToOne // hoặc CascadeType.ALL
        private NhanVien nhanVien;


        private Date ngayLam;
        @DateTimeFormat(pattern = "HH:mm:ss")
        private LocalTime gioBatDau;
        @DateTimeFormat(pattern = "HH:mm:ss")
        private LocalTime gioKetThuc;
        private String tenCongViec;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Nullable
        public VanPhong getVpCongTac() {
            return vpCongTac;
        }

        public void setVpCongTac(@Nullable VanPhong vpCongTac) {
            this.vpCongTac = vpCongTac;
        }

        public TrangThaiLamViec getTrangThai() {
            return trangThai;
        }

        public void setTrangThai(TrangThaiLamViec trangThai) {
            this.trangThai = trangThai;
        }

        public NhanVien getNhanVien() {
            return nhanVien;
        }

        public void setNhanVien(NhanVien nhanVien) {
            this.nhanVien = nhanVien;
        }

        public Date getNgayLam() {
            return ngayLam;
        }

        public void setNgayLam(Date ngayLam) {
            this.ngayLam = ngayLam;
        }

        public LocalTime getGioBatDau() {
            return gioBatDau;
        }

        public void setGioBatDau(LocalTime gioBatDau) {
            this.gioBatDau = gioBatDau;
        }

        public LocalTime getGioKetThuc() {
            return gioKetThuc;
        }

        public void setGioKetThuc(LocalTime gioKetThuc) {
            this.gioKetThuc = gioKetThuc;
        }

        public String getTenCongViec() {
            return tenCongViec;
        }

        public void setTenCongViec(String tenCongViec) {
            this.tenCongViec = tenCongViec;
        }


    }