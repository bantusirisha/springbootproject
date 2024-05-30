package siri.employeeProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siri.employeeProject.entity.EmpPhoneNum;
import siri.employeeProject.repository.EmpPhnNoRepo;
import siri.employeeProject.repository.EmployeeRepo;

import java.util.List;

@Service
public class EmpPhnNoServie {

    @Autowired
    private EmpPhnNoRepo empPhnNoRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<EmpPhoneNum> getAll() {
        return empPhnNoRepo.findAll();
    }

    public EmpPhoneNum insert(EmpPhoneNum empPhoneNum) {
        return empPhnNoRepo.save(empPhoneNum);
    }


    public String delete(int id) {
        empPhnNoRepo.deleteById(id);
        return id+" is successfully deleted";
    }

}
