package vn.edu.iuh.backend.business;

import vn.edu.iuh.backend.entities.Employee;

import java.util.List;

public interface EmployeeRemote {
    void addEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee getEmployeeById(Long id);
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
