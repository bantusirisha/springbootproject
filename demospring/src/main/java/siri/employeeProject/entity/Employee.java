package siri.employeeProject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int empId;

    private String firstName;
    private String lastName;
    private int expYear;

    @OneToMany(cascade = CascadeType.ALL , fetch =FetchType.EAGER)
    @JoinColumn(name = "empId", referencedColumnName = "empId")
    List<EmpPhoneNum> empPhoneNums;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ref_id", referencedColumnName = "id")
    EmpCredentials empCredentials;

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", expYear=" + expYear +
                ", empPhoneNums=" + empPhoneNums +
                ", empCredentials=" + empCredentials +
                '}';
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public List<EmpPhoneNum> getEmpPhoneNums() {
        return empPhoneNums;
    }

    public void setEmpPhoneNums(List<EmpPhoneNum> empPhoneNums) {
        this.empPhoneNums = empPhoneNums;
    }

    public EmpCredentials getEmpCredentials() {
        return empCredentials;
    }

    public void setEmpCredentials(EmpCredentials empCredentials) {
        this.empCredentials = empCredentials;
    }
}
