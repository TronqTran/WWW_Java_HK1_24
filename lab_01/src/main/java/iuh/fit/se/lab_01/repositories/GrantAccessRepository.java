package iuh.fit.se.lab_01.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class GrantAccessRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public GrantAccessRepository() {
        // create entity manager and entity transaction
        this.entityManager = Persistence.createEntityManagerFactory("dblab01").createEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }
    public void addGrantAccess(String role_id, String account_id) {
        // add grant access
        try {
            entityTransaction.begin();
            entityManager.createNativeQuery("INSERT INTO grant_access (role_id, account_id) VALUES (?, ?, ?)")
                    .setParameter(1, role_id)
                    .setParameter(2, account_id)
                    .setParameter(3, 1)
                    .executeUpdate();
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
    public void removeGrantAccess(String role_id, String account_id) {
        // remove grant access
        try {
            entityTransaction.begin();
            entityManager.createNativeQuery("DELETE FROM grant_access WHERE role_id = ? AND account_id = ?")
                    .setParameter(1, role_id)
                    .setParameter(2, account_id)
                    .executeUpdate();
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

}