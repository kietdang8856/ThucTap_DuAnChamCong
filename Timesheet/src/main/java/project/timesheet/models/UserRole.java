package project.timesheet.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Entity
@Data
@Table(name = "users_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;

    public UserRole(NhanVien nhanVien, Role role) {
        this.nhanVien = nhanVien;
        this.role = role;
    }

    public UserRole() {}

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}