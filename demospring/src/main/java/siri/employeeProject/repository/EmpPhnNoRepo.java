package siri.employeeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siri.employeeProject.entity.EmpPhoneNum;

public interface EmpPhnNoRepo extends JpaRepository<EmpPhoneNum, Integer> {

}
