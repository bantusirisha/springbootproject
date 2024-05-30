package siri.employeeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import siri.employeeProject.entity.EmpCredentials;

public interface EmpCredRepo extends JpaRepository<EmpCredentials, Integer> {
    @Query(value = "select e from EmpCredentials e where e.userName=:username and e.password=:password")
    EmpCredentials findEmpByCred(String username,String password);
}
