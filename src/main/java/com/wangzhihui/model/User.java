package com.wangzhihui.model;
import java.util.Date;

public class User {
    //one row in usertable == one object of User
    private int id;
    private String username;
    private String password;
    private String email;
    private String gender;
    private Date birthDate;

    public User(){
    }
    //full

    public User(int id, String name, String password, String email, String gender, Date birthdate) {
        super();
        this.id = id;
        this.username = name;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthdate;
    }

    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getBirthdate() {
        return birthDate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthDate = birthdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
