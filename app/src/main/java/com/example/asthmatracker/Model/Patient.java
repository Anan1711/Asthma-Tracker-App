package com.example.asthmatracker.Model;

public class Patient {
    private String fullName, email, password, age, phone, weight;

    public  Patient(){

    }

    public Patient(String fullName, String email, String password, String age, String phone, String weight) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phone = phone;
        this.weight = weight;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
