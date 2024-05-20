package project.timesheet.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ChucVu")

public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String TenChucVu;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "chucvu",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<NhanVien> nhanViens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        TenChucVu = tenChucVu;
    }
}
