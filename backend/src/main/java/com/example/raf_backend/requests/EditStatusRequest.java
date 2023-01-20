package com.example.raf_backend.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EditStatusRequest {

    @NotNull(message = "Status field required")
    @NotEmpty(message = "Status field required")
    private boolean status;

    @NotNull(message = "Email field required")
    @NotEmpty(message = "Email field required")
    private String email;

    public EditStatusRequest() {
    }

    public EditStatusRequest(boolean status, String email) {
        this.status = status;
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
