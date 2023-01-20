package com.example.raf_backend.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EditUserRequest {

    @NotNull(message = "OldEmail field required")
    @NotEmpty(message = "OldEmail field required")
    private String oldEmail;

    @NotNull(message = "NewEmail field required")
    @NotEmpty(message = "NewEmail field required")
    private String newEmail;

    @NotNull(message = "Name field required")
    @NotEmpty(message = "Name field required")
    private String name;

    @NotNull(message = "Surname field required")
    @NotEmpty(message = "Surname field required")
    private String surname;

    @NotNull(message = "UserType field required")
    @NotEmpty(message = "UserType field required")
    private String userType;

    public EditUserRequest() {
    }


    public EditUserRequest(String oldEmail, String newEmail, String name, String surname, String userType) {
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
        this.name = name;
        this.surname = surname;
        this.userType = userType;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
