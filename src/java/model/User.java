/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Boolean isSubscribe;
    private String userType;
    private String location;

    public User(String name, String email, String password,  boolean isSubscribe, String userType, String location) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isSubscribe = isSubscribe;
        this.userType = userType;
        this.location = location;
    }

    public User(int id, String name, String email, String password,  boolean isSubscribe, String userType, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isSubscribe = isSubscribe;
        this.userType = userType;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsSubscribe() {
        return isSubscribe;
    }

    public String getUserType() {
        return userType;
    }

    public String getLocation() {
        return location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsSubscribe(Boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    

}