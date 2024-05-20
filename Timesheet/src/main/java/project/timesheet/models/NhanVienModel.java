package project.timesheet.models;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class NhanVienModel {
    private String tenNV;
    private int chucVu_id;
    private int vpLamViecChinh_id;
    private String sdt;
    private String diaChi;
    private Date ngayBatDauLam;
    private boolean gioiTinh;
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private int enabled;

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getChucVu_id() {
        return chucVu_id;
    }

    public void setChucVu_id(int chucVu_id) {
        this.chucVu_id = chucVu_id;
    }

    public int getVpLamViecChinh_id() {
        return vpLamViecChinh_id;
    }

    public void setVpLamViecChinh_id(int vpLamViecChinh_id) {
        this.vpLamViecChinh_id = vpLamViecChinh_id;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }


    public Date getNgayBatDauLam() {
        return ngayBatDauLam;
    }

    public void setNgayBatDauLam(Date ngayBatDauLam) {
        this.ngayBatDauLam = ngayBatDauLam;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
