package com.feedback.impluse;

public class UserInfoClassData {

    private String cell,name,email,city,status,user_id,password,user_cnic,secondary_contact;
    private String address,user_image;

    public UserInfoClassData(String cell, String name, String email, String city, String status, String user_id) {
        this.cell = cell;
        this.name = name;
        this.email = email;
        this.city = city;
        this.status = status;
        this.user_id = user_id;

    }

    public UserInfoClassData(String cell, String name, String email, String status, String user_id) {
        this.cell = cell;
        this.name = name;
        this.email = email;
        this.status = status;
        this.user_id = user_id;

    }

    public String getSecondary_contact() {
        return secondary_contact;
    }

    public void setSecondary_contact(String secondary_contact) {
        this.secondary_contact = secondary_contact;
    }

    public UserInfoClassData() {
        this.cell = "cell";
        this.name = "name";
        this.email = "email";
        this.city = "city";
        this.status = "active";
        this.user_id = "userid";
        this.password = "12345";

        this.address = "Islamabad";
    }

    public String getUser_cnic() {
        return user_cnic;
    }

    public void setUser_cnic(String user_cnic) {
        this.user_cnic = user_cnic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCell() {
        return cell;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getStatus() {
        return status;
    }

    public String getUser_id() {
        return user_id;
    }


    public String getAddress() {
        return address;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }
}
