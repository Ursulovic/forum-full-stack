package com.example.raf_backend.repositories.user;

import com.example.raf_backend.entities.User;
import com.example.raf_backend.requests.EditStatusRequest;
import com.example.raf_backend.requests.EditUserRequest;

import java.util.List;

public interface UserRepository {
    public List<User> fetchUsers(int page);
    public int getUserNumber();
    public boolean addUser(User user);
    public boolean editUser(EditUserRequest request);
    public boolean changeStatus(EditStatusRequest request);
    public User findUser(String email);
}
