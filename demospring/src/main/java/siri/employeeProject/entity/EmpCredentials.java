package siri.employeeProject.entity;

import jakarta.persistence.*;

@Entity
@Table(name="empCredentials")
public class EmpCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String userName;
    private String password;

    @OneToOne(mappedBy = "empCredentials")
    Employee employee;


    @Override
    public String toString() {
        return "EmpCredentials{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
