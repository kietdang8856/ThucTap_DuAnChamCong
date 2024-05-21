    package project.timesheet.models;
    import jakarta.annotation.Nullable;
    import jakarta.persistence.*;
    import lombok.*;

    import java.sql.Blob;
    import java.sql.Date;
    import java.util.HashSet;
    import java.util.Set;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "NhanVien")

    public class NhanVien {
        @Getter
        @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Getter
        @Setter
        private String tenNV;

        @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinColumn(name = "chucvu_id", updatable = true, insertable = true)
        private ChucVu chucvu;

        @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinColumn(name = "vplamviecchinh_id", updatable = true, insertable = true)
        private VanPhong vpLamViecChinh;

        @Getter
        @Setter
        @Nullable
        private String sdt;

        @Getter
        @Setter
        @Nullable
        private String diaChi;

        @Getter
        @Setter
        @Nullable
        @Column(name = "avatar")
        private String avatarPath; // Lưu trữ đường dẫn tương đối tới ảnh

        @Getter
        @Setter
        @Nullable
        private Date ngayBatDauLam;

        @Getter
        @Setter
        @Nullable
        private int gioiTinh;

        @Getter
        @Setter
        @Nullable
        private String email;

        @Getter
        @Setter
        @Column(name = "username")
        private String username;

        @Getter
        @Setter
        @Column(name = "password")
        private String password;

        @Getter
        @Setter
        @Column(name = "enabled")
        private int enabled;


        @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<UserRole> userRoles = new HashSet<>();

        public ChucVu getChucvu() {
            return chucvu;
        }

        public void setChucvu(ChucVu chucvu) {
            this.chucvu = chucvu;
        }

        public VanPhong getVpLamViecChinh() {
            return vpLamViecChinh;
        }

        public void setVpLamViecChinh(VanPhong vpLamViecChinh) {
            this.vpLamViecChinh = vpLamViecChinh;
        }


    }