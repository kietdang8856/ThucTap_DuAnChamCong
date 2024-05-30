package project.timesheet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VanPhong")
public class VanPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;
    @Column(name = "tenvp")
    private String tenVP;
    @Column(name = "diachi")
    private String diaChi;
    @OneToMany
    @Column(name = "lichlamviec")
    private List<LichLamViec> lichLamViecs ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenVP() {
        return tenVP;
    }

    public void setTenVP(String tenVP) {
        this.tenVP = tenVP;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

}