package project.timesheet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

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
    @ManyToOne
    private NhanVien nhanVien;
    private Date ngayLam;
    private int gioBatDau;
    private int gioKetThuc;
    private String tenCongViec;

}
