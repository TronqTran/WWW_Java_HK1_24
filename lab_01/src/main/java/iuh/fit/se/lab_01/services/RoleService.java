package iuh.fit.se.lab_01.services;

import iuh.fit.se.lab_01.models.Role;
import iuh.fit.se.lab_01.repositories.RoleRepository;

import java.util.List;

public class RoleService {
    private RoleRepository roleRepositories;
    public RoleService() {
        this.roleRepositories = new RoleRepository();
    }
    public void addRole(Role role) {
        roleRepositories.addRole(role);
    }
    public void updateRole(Role role) {
        roleRepositories.updateRole(role);
    }
    public void deleteRole(Role role) {
        roleRepositories.deleteRole(role);
    }
    public Role findById(String id){
        return roleRepositories.findById(id);
    }
    public List<Role> getAllRoles() {
        return roleRepositories.getAllRoles();
    }
}
