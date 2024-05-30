package siri.employeeProject.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name="categories")
public class Category {

    @Id
    @Column(name="catcode")
    private String catcode;
    private String catdesc;

    @OneToMany(mappedBy = "category")
    List<Product> products=new ArrayList<>();

    public String getCatcode() {
        return catcode;
    }

    public void setCatcode(String catcode) {
        this.catcode = catcode;
    }

    public String getCatdesc() {
        return catdesc;
    }

    public void setCatdesc(String catdesc) {
        this.catdesc = catdesc;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catcode='" + catcode + '\'' +
                ", catdesc='" + catdesc + '\'' +
                '}';
    }
}
