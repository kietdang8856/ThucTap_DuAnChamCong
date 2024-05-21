    package project.timesheet.models;
    import jakarta.annotation.Nullable;
    import jakarta.persistence.*;
    import lombok.*;

    import java.sql.Blob;
    import java.sql.Date;
    import java.util.HashSet;
    import java.util.Objects;
    import java.util.Set;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "NhanVien")
    public class NhanVien {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String tenNV;

        @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinColumn(name = "chucvu_id", updatable = true, insertable = true)
        private ChucVu chucvu;

        @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinColumn(name = "vplamviecchinh_id", updatable = true, insertable = true)
        private VanPhong vpLamViecChinh;

        @Nullable
        private String sdt;

        @Nullable
        private String diaChi;

        @Nullable
        @Column(name = "avatar")
        private String avatarPath; // Lưu trữ đường dẫn tương đối tới ảnh

        @Nullable
        private Date ngayBatDauLam;

        @Nullable
        private int gioiTinh;

        @Nullable
        private String email;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        @Column(name = "enabled")
        private int enabled;

        @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<UserRole> userRoles = new HashSet<>();

        @Override
        public int hashCode() {
            return Objects.hash(id, tenNV, sdt, diaChi, avatarPath, ngayBatDauLam, gioiTinh, email, username, password, enabled);
        }
    }