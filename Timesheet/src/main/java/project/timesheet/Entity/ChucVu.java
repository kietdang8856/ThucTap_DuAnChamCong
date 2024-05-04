package DuAnChamCong.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Literal;

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
    private int id;
    private String TenChucVu;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "chucvu")
    private List<NhanVien> nhanViens
            = new ArrayList<>();

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
