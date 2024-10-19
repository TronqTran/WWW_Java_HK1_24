package iuh.fit.se.lab_01.repositories;

import iuh.fit.se.lab_01.models.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class AccountRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public AccountRepository() {
        this.entityManager = Persistence.createEntityManagerFactory("dblab01").createEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }
    public void addAccuont(Account account) {
        // Add account to database
        try {
            entityTransaction.begin();
            entityManager.persist(account);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }

    }
    public void updateAccount(Account account) {
        // Update account in database
        try {
            entityTransaction.begin();
            entityManager.merge(account);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
    public void deleteAccount(Account account) {
        // Delete account from database
        try {
            entityTransaction.begin();
            entityManager.remove(account);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
    public Account findById(String account_id) {
        // Get account from database
        try {
            return entityManager.find(Account.class, account_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Account findByEmailAndPassword(String email, String password) {
        // Get account from database by email and password
        try {
            return entityManager.createQuery("SELECT a FROM Account a WHERE a.email = :email AND a.password = :password", Account.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Account> getAllAccounts() {
        // Get all accounts from database
        try {
            return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void isAdminAccount() {
        // find admin account
        try {
            entityTransaction.begin();
            entityManager.createNativeQuery("SELECT * FROM account WHERE account_id IN (SELECT account_id FROM grant_access WHERE role_id = 'ADMIN')")
                    .getResultList()
                    .forEach(System.out::println);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
}
