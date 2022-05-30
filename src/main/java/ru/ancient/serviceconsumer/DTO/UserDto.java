package ru.ancient.serviceconsumer.DTO;


import ru.ancient.serviceconsumer.enums.CompanyEnum;
import ru.ancient.serviceconsumer.enums.Sex;

public class UserDto {


    private int id;

    private String name;

    private String company;


    private String department;


    private int salary;


    private Sex sex;


    public UserDto() {
    }

    public UserDto(String name, String company, String department, int salary, Sex sex) {
        this.name = name;
        this.company = company;
        this.department = department;
        this.salary = salary;
        this.sex = sex;
    }


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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", sex=" + sex +
                '}';
    }
}
