package siri.employeeProject.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.sound.midi.Sequence;

//@Entity
//@Table(name="prod")
public class Prod {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Prod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }












    //    @Id
//    @GeneratedValue
//    private int prodid;
//    private String prodname;
////    private int catc;
////
////    public Prod(int prodid, String prodname, int catc) {
////        this.prodid = prodid;
////        this.prodname = prodname;
////        this.catc = catc;
////    }
//
//    public Prod() {
//    }
//
////    @ManyToOne
////    @JoinColumn(name = "catc" , insertable = false , updatable = false)
////    private Cat cat;
//    public int getProdid() {
//        return prodid;
//    }
//
//    public void setProdid(int prodid) {
//        this.prodid = prodid;
//    }
//
//    public String getProdname() {
//        return prodname;
//    }
//
//    public void setProdname(String prodname) {
//        this.prodname = prodname;
//    }
//
////    public int getCatc() {
////        return catc;
////    }
////
////    public void setCatc(int catc) {
////        this.catc = catc;
////    }
//
//    @Override
//    public String toString() {
//        return "Prod{" +
//                "prodid=" + prodid +
//                ", prodname='" + prodname + '\'' +
////                ", catc='" + catc + '\'' +
//                '}';
//    }
}
