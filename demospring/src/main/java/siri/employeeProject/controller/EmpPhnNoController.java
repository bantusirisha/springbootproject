package siri.employeeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siri.employeeProject.entity.EmpPhoneNum;
import siri.employeeProject.service.EmpPhnNoServie;

import java.util.List;

@RestController
public class EmpPhnNoController {

    @Autowired
    private EmpPhnNoServie empPhnNoServie;

    @GetMapping("/getAllEmpPhnNums")
    public List<EmpPhoneNum> getAll(){
        return empPhnNoServie.getAll();
    }

    @PostMapping("/insertPhnNos")
    public EmpPhoneNum insertPhnNos(@RequestBody EmpPhoneNum empPhoneNum){
        return empPhnNoServie.insert(empPhoneNum);
    }

    @DeleteMapping("/deletePhnNo/{id}")
    public String deletePhnNos(@PathVariable int id){
        return empPhnNoServie.delete(id);
    }


}