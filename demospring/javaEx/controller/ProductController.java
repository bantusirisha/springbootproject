package siri.employeeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import siri.employeeProject.entity.Product;

import java.util.List;

//@RestController
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

//    @GetMapping("getPoductsByCat/{catcode}")
//    public List<Product> getProdsByCat(@PathVariable("catcode") String catcode){
//        var category=categoryRepo.findByCode(catcode);
//        List<Product> products=productRepo.findByCategory(category);
//        return  products;
//    }

//    //retrieve products by category
//    @GetMapping("/products/categories/{code}")
//    public List<Product> getProductsByCategory(@PathVariable("code") String code){
//        var category=categoryRepo.findByCode(code);
//        if(category == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Code Not Found!");
//        }
//        else {
//            List<Product> products=productRepo.findByCategory(category);
//            if(products.isEmpty()) {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products are Found!");
//            }
//            else {
//                return products;
//            }
//        }
//}
}
