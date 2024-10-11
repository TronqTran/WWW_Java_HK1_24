package iuh.fit.se.lab_01.repositories;

import iuh.fit.se.lab_01.models.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class LogRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public LogRepository() {
        // create entity manager and entity transaction
        this.entityManager = Persistence.createEntityManagerFactory("dblab01").createEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }

    public void addLog(Log log) {
        // add log
        try {
            entityTransaction.begin();
            entityManager.persist(log);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
    public List<Log> getAllLogs() {
        // get all logs
        try {
            return entityManager.createQuery("SELECT l FROM Log l", Log.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateLog(Log log) {
        // update log
        try {
            entityTransaction.begin();
            entityManager.merge(log);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
}
