package siri.employeeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siri.employeeProject.entity.Prod;

import java.util.List;

//@RestController
public class ProdController {

    @Autowired
    private ProdRepo prodrepo;

//    @Autowired
//    private CatRepo catrepo;

    @GetMapping("/getallprods")
    public List<Prod> getAllProds(){
        return prodrepo.findAll();
    }

    @PostMapping("/setprods")
    public Prod getAllProds(@RequestBody Prod prod){
        return prodrepo.save(prod);
    }
//
//    @PutMapping("/updateprod/{prodid}")
//    public List<Prod> updateAllProds(@PathVariable("id") int id,@RequestBody Prod prod){
//        var optProd=prodrepo.findById(id);
//        if(optProd.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID is not found");
//        }
//        else{
//            prodrepo.save(prod);
//        }
//        return prodrepo.findAll();
//    }
//
//    @DeleteMapping("/deleteprod/{id}")
//    public List<Prod> deleteOneProd(@PathVariable("id") int id) {
//        var optProd = prodrepo.findById(id);
//        if (optProd.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "prod id is not found");
//        } else {
//            prodrepo.deleteById(id);
//        }
//        return prodrepo.findAll();
//    }

    //get prods by catc
//    @GetMapping("/getprodbycatc/{catc}")
//    public List<Prod> getProdsByCatC(@PathVariable(name="catc") String catc){
//        var optCat=catrepo.findByCatC(catc);
//        if(optCat==null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "catc is not found");
//        }
//        else{
//            List<Prod> prod=prodrepo.findByCat(optCat);
//            return prod;
//        }
//
//    }
}
