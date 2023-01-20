package com.example.raf_backend.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LoginRequest implements Serializable {

    @NotNull(message = "Username field required")
    @NotEmpty(message = "Username field required")
    private String email;

    @NotNull(message = "Password field required")
    @NotEmpty(message = "Password field required")
    private String password;

    public LoginRequest() {
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
}
