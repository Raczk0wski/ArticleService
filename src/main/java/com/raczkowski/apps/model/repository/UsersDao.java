package com.raczkowski.apps.model.repository;


import com.raczkowski.apps.model.User;
import com.raczkowski.apps.model.UserRegistrationData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao {

    void addUser(UserRegistrationData user);

    List<User> loadUsers();

    void updateUserData(int id, String email, String password, String name, String lastName);
}
