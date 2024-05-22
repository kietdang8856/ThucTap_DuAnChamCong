package project.timesheet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="users_roles")
public class UserRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private NhanVien NhanVien;
    @Getter
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;
    public UserRole(NhanVien NhanVien, Role role) {
        this.NhanVien = NhanVien;
        this.role = role;
    }

    public UserRole() {

    }
}
