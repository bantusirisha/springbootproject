package siri.employeeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siri.employeeProject.entity.Category;


public interface CategoryRepo extends JpaRepository<Category, String> {

    //Category findByCode(String catcode);

}