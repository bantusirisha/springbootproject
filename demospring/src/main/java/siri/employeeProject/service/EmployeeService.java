package siri.employeeProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siri.employeeProject.entity.Employee;
import siri.employeeProject.repository.EmployeeRepo;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }
    public Employee saveEmployees(Employee employee){
        employeeRepo.save(employee);
        return employee;
    }


    public Employee updateEmployees(Employee employee){
        var optEmp=employeeRepo.findById(employee.getEmpId());
        var emp=optEmp.get();
        emp.setEmpId(employee.getEmpId());
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setExpYear(employee.getExpYear());
        emp.setEmpPhoneNums(employee.getEmpPhoneNums());
        emp.setEmpCredentials(employee.getEmpCredentials());
        employeeRepo.save(emp);
        return emp;
    }

    public String deleteEmployee(int id){
        employeeRepo.deleteById(id);
        return id+" is successfully deleted";
    }

}
