package vn.edu.iuh.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.backend.entities.Employee;

import java.util.List;

public class EmployeeRepository {
    @PersistenceContext(name = "lab02PU")
    private EntityManager entityManager;
    public void addEmployee(Employee employee) {
        try {
            entityManager.persist(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateEmployee(Employee employee) {
        try {
            entityManager.merge(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Employee getEmployeeById(Long id) {
        return entityManager.find(Employee.class, id);
    }
    public List<Employee> getAllEmployees() {
        return entityManager.createNamedQuery("Employee.findAll").getResultList();
    }


}
