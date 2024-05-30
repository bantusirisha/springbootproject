package siri.employeeProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siri.employeeProject.entity.EmpCredentials;
import siri.employeeProject.repository.EmpCredRepo;

import java.util.List;

@Service
public class EmpCredService {

    @Autowired
    private EmpCredRepo empCredRepo;

    public List<EmpCredentials> getAll(){
        return empCredRepo.findAll();
    }

    public EmpCredentials insert(EmpCredentials empCredentials){
        return empCredRepo.save(empCredentials);
    }

    public EmpCredentials getEmpCred(String username, String password) {
        return empCredRepo.findEmpByCred(username,password);
    }

}
