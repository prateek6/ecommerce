package controller;

import domain.user.User;
import domain.user.impl.UserServiceImpl;

import java.util.Objects;

public class UserController {
    UserServiceImpl userService = new UserServiceImpl();

    public boolean signUp(User user){
        return userService.signUp(user);
    }


    public boolean logIn(User user) {
        return userService.logIn(user);
    }

    public boolean logout(User user) {
        return userService.logOut(user);
    }

    public boolean isUserAdmin(User user) {
        return userService.isUserAdmin(user);
    }

    public boolean isUserExits(String email) {
        if (userService.isUserExists(email)) {
            throw new IllegalArgumentException("User already exits");
        }
        return false;
    }

    public User getUser(String email){
        User user = new User();
        user.setEmail(email);
        if (Objects.isNull(userService.getUser(user))) {
            throw new RuntimeException("user doesn't exist ");
        }
        return UserServiceImpl.userInMemoryDatabase.get(email);
    }
}
