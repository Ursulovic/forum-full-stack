package com.example.raf_backend.requests;

public class EditCategoryRequest {
    private String oldName;
    private String newName;
    private String newDescription;

    public EditCategoryRequest(String oldName, String newName, String newDescription) {
        this.oldName = oldName;
        this.newName = newName;
        this.newDescription = newDescription;
    }

    public EditCategoryRequest() {
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
}
