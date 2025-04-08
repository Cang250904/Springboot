package com.example.mobile_store.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {}

    // Constructor có tham số
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter cho username
    public String getUsername() {
        return username;
    }

    // Setter cho username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter cho password
    public String getPassword() {
        return password;
    }

    // Setter cho password
    public void setPassword(String password) {
        this.password = password;
    }
}


