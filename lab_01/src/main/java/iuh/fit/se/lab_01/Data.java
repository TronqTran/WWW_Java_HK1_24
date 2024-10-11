package iuh.fit.se.lab_01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Data {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("dblab01").createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityTransaction.commit();
        entityManager.close();
    }
}
