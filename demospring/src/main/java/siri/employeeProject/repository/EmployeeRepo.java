package siri.employeeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siri.employeeProject.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}
