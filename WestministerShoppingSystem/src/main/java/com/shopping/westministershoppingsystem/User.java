package com.shopping.westministershoppingsystem;

public class User {
    private String username;
    private String password;

    // Constructors
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and Setter methods
    public String getUsername() {
        return username;
    }

    // Setter for setting the usernamne
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for getting the password
    public String getPassword() {
        return password;
    }

    // Setter for setting the passsword
    public void setPassword(String password) {
        this.password = password;
    }
}

