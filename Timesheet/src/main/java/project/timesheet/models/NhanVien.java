package project.timesheet.models;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.Date;

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
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "chucvu_id",updatable = true, insertable = true)
    private ChucVu chucvu;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private VanPhong vpLamViecChinh;
    @Nullable
    private String sdt;
    @Nullable
    private String diaChi;
    @Nullable
    private Blob avatar;
    @Nullable
    private Date ngayBatDauLam;
    @Nullable
    private boolean gioiTinh;
    @Nullable
    private String email;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

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

    @Nullable
    public String getSdt() {
        return sdt;
    }

    public void setSdt(@Nullable String sdt) {
        this.sdt = sdt;
    }

    @Nullable
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(@Nullable String diaChi) {
        this.diaChi = diaChi;
    }

    @Nullable
    public Date getNgayBatDauLam() {
        return ngayBatDauLam;
    }

    public void setNgayBatDauLam(@Nullable Date ngayBatDauLam) {
        this.ngayBatDauLam = ngayBatDauLam;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }


}