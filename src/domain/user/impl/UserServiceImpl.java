package domain.user.impl;

import domain.user.User;
import domain.user.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    public static Map<String,User> userInMemoryDatabase = new HashMap<>();


    @Override
    public boolean signUp(User user) {
        userInMemoryDatabase.put(user.getEmail(),user);
        return true;
    }

    @Override
    public boolean logIn(User user) {
        User userFromDb = userInMemoryDatabase.get(user.getEmail());
        userFromDb.setLoggedIn(true);
        return true;
    }

    @Override
    public boolean logOut(User user) {
        User userFromDb = userInMemoryDatabase.get(user.getEmail());
        userFromDb.setLoggedIn(false);
        return true;
    }

    @Override
    public boolean isUserAdmin(User user) {
        return user.isAdmin();
    }

    @Override
    public boolean isUserExists(String email) {
        return userInMemoryDatabase.containsKey(email);
    }

    @Override
    public User getUser(User user) {
        return userInMemoryDatabase.get(user.getEmail());
    }
}
