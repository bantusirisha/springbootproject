package siri.employeeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siri.employeeProject.entity.Prod;

public interface ProdRepo extends JpaRepository<Prod, Integer> {

//    List<Prod> findByCat(Cat cat);
}
