package com.example.demo;

import databasecon.DataBase;
import models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private DataBase db;

    public UserService() {
        this.db = new DataBase("users.sqlite");
    }

    public List<User> getAll() {
        return db.selectAll();
    }
}
