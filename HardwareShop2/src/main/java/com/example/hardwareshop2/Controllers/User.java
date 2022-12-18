package com.example.hardwareshop2.Controllers;

public class User {
    static String userStatus="Employee";
    public User(String userType,String userName,String password,String name, Date dateOfJoining) {
        this.name=name;
        this.userType = userType;
        this.userName = userName;
        this.password = password;
        this.dateOfJoining=dateOfJoining;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public String getName() {
        return name;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setName(String name) {
        this.name = name;
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

    private String userType;
    private String userName;
    private String password;
    private Date dateOfJoining;
    private String name;

}