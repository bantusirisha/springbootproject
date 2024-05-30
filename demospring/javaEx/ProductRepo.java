package siri.employeeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siri.employeeProject.entity.Category;
import siri.employeeProject.entity.Prod;
import siri.employeeProject.entity.Product;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    //List<Product> findByCategory(Category category);
}
