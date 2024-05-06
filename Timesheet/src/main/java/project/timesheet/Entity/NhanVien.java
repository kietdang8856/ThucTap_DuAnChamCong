package project.timesheet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "chucvu_id", insertable = false, updatable = false)
    private ChucVu chucvu;
    @ManyToOne
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


}
