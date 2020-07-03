package domain.user;

public interface UserService {
     boolean signUp(User user);
     boolean logIn(User user);
     boolean logOut(User user);

    boolean isUserAdmin(User user);

    boolean isUserExists(String email);

    User getUser(User user);
}
