package iuh.fit.se.lab_01.repositories;

import iuh.fit.se.lab_01.models.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class RoleRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public RoleRepository() {
        this.entityManager = Persistence.createEntityManagerFactory("dblab01").createEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }
    public void addRole(Role role) {
        // Add role to database
        try {
            entityTransaction.begin();
            entityManager.persist(role);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
    public void updateRole(Role role) {
        // Update role in database
        try {
            entityTransaction.begin();
            entityManager.merge(role);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
    public void deleteRole(Role role) {
        // Delete role from database
        try {
            entityTransaction.begin();
            entityManager.remove(role);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
    public Role findById(String id) {
        // Get role from database
        try {
            return entityManager.find(Role.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Role> getAllRoles() {
        // Get all roles from database
        try {
            return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
