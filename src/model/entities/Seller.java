package model.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Date;

public class Seller implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Date date;
    private Double salary;

    private Department department;

    private static final long serialVersionUID = 1L;

    public Seller() {
    }

    public Seller(Double salary, Date date, String email, String name, Integer id, Department department) {
        this.salary = salary;
        this.date = date;
        this.email = email;
        this.name = name;
        this.id = id;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
