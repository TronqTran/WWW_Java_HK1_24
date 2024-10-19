package iuh.fit.se.lab_01.models;

import iuh.fit.se.lab_01.enums.RoleStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Role {
    @Id
    private String role_id;
    private String role_name;
    private String description;
    private RoleStatus status;

    @OneToMany
    @JoinColumn(name = "role_id")
    private List<GrantAccess> grantAccess;
    public Role() {
    }

    public Role(String role_id, String role_name, String description, RoleStatus status) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.description = description;
        this.status = status;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleStatus getStatus() {
        return status;
    }

    public void setStatus(RoleStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(role_id, role.role_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + role_id + '\'' +
                ", role_name='" + role_name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
