package vn.edu.iuh.backend.business.beans;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.backend.business.EmployeeRemote;
import vn.edu.iuh.backend.entities.enums.EmployeeStatus;
import vn.edu.iuh.backend.repositories.EmployeeRepository;
import vn.edu.iuh.backend.entities.Employee;

import java.util.List;
@Stateless
public class EmployeeBean implements EmployeeRemote {
    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        this.getEmployeeById(id).setStatus(EmployeeStatus.TERMINATED);
    }
}
