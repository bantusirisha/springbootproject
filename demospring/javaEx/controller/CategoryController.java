package siri.employeeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import siri.employeeProject.entity.Category;

import java.util.List;

//@RestController
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories(){
        return categoryRepo.findAll();
    }
}
