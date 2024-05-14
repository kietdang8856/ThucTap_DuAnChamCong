package project.timesheet.models;

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
    private User user;
    @Getter
    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public UserRole() {

    }
}
