package project.timesheet.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
public class LichLamViecModel {

    private int  vpCongTac_id;
    private int trangThaiLamViec_Id;
    private int nhanVien_id;
    private Date ngayLam;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;
    private String tenCongViec;

    public int getVpCongTac_id() {
        return vpCongTac_id;
    }

    public void setVpCongTac_id(int vpCongTac_id) {
        this.vpCongTac_id = vpCongTac_id;
    }

    public int getTrangThaiLamViec_Id() {
        return trangThaiLamViec_Id;
    }

    public void setTrangThaiLamViec_Id(int trangThaiLamViec_Id) {
        this.trangThaiLamViec_Id = trangThaiLamViec_Id;
    }

    public int getNhanVien_id() {
        return nhanVien_id;
    }

    public void setNhanVien_id(int nhanVien_id) {
        this.nhanVien_id = nhanVien_id;
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