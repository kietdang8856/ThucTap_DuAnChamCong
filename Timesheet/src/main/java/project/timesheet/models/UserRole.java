package project.timesheet.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name="users_roles")
public class UserRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private NhanVien nhanVien;
    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;
    public UserRole(NhanVien NhanVien, Role role) {
        this.nhanVien = NhanVien;
        this.role = role;
    }

    public UserRole() {

    }
}
