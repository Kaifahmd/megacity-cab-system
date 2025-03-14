package com.rental.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String email;
    private String idNo;
    private String password;
    private String userType;

    public User(int id, String name, String email, String idNo, String password, String userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.idNo = idNo;
        this.password = password;
        this.userType = userType;
    }

    public User(String name, String email, String idNo, String password, String userType) {
        this.name = name;
        this.email = email;
        this.idNo = idNo;
        this.password = password;
        this.userType = userType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
