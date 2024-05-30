package siri.employeeProject.entity;

import jakarta.persistence.*;

@Entity
@Table(name="empTableNo")
public class EmpPhoneNum {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String officeNum;
    private String mobileNum;

    @Override
    public String toString() {
        return "EmpPhoneNum{" +
                "id=" + id +
                ", officeNum='" + officeNum + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(String officeNum) {
        this.officeNum = officeNum;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }
}
