package com.example.raf_backend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class    User {

    @NotNull(message = "email field required")
    @NotEmpty(message = "email field required")
    private String email;

    @NotNull(message = "name field required")
    @NotEmpty(message = "name field required")
    private String name;

    @NotNull(message = "surname field required")
    @NotEmpty(message = "surname field required")
    private String surname;

    @NotNull(message = "type field required")
    @NotEmpty(message = "type field required")
    private String type;

    @NotNull(message = "status field required")
    @NotEmpty(message = "status field required")
    private boolean status;

    @NotNull(message = "password field required")
    @NotEmpty(message = "password field required")
    private String password;

    public User(String email, String name, String surname, String type, boolean status, String password) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.status = status;
        this.password = password;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}
