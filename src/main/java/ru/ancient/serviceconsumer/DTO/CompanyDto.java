package ru.ancient.serviceconsumer.DTO;

import java.util.List;


public class CompanyDto {

    private String name;
    private String address;
    private int phoneNumber;
    private String weburl;
    private List<UserDto> employees;

    public List<UserDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<UserDto> users) {
        this.employees = users;
    }

    public CompanyDto() {
    }

    public CompanyDto(String name, String address, int phoneNumber, String weburl) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.weburl = weburl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }


    @Override
    public String toString() {
        return "CompanyDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", weburl='" + weburl + '\'' +
                '}';
    }
}
