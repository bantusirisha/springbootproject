package siri.employeeProject.entity;

import jakarta.persistence.*;

//@Entity
//@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int prodid;
    private String prodname;
    private String catcode;

    @ManyToOne
    @JoinColumn(name="catcode",insertable=false,updatable=false)
    private Category category;

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getCatcode() {
        return catcode;
    }

    public void setCatcode(String catcode) {
        this.catcode = catcode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodid=" + prodid +
                ", prodname='" + prodname + '\'' +
                ", catcode='" + catcode + '\'' +
                '}';
    }

}
