package siri.employeeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siri.employeeProject.entity.EmpCredentials;
import siri.employeeProject.service.EmpCredService;

import java.util.List;

@RestController
public class EmpCredController {

    @Autowired
    private EmpCredService empCredService;

    @GetMapping("/getAllEmpCred")
    public List<EmpCredentials> getAllCred(){
        return empCredService.getAll();
    }

    @PostMapping("/insertData")
    public EmpCredentials insertData(EmpCredentials empCredentials){
        return empCredService.insert(empCredentials);
    }

    @GetMapping("/getEmpByCred")
    public EmpCredentials getEmps(@RequestParam String username, @RequestParam String password){
        return empCredService.getEmpCred(username,password);
    }

}
