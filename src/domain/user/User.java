package domain.user;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    //name, user id, address, date of birth

    private String name;
    private long userId;
    private String address;
    private String dateOfBirth;
    private boolean isLoggedIn;
    private String password;
    private boolean isAdmin;
    private String email;
    private static final AtomicInteger count = new AtomicInteger(0);

    public User(){
        userId = count.incrementAndGet();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isLoggedIn=" + isLoggedIn +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", email='" + email + '\'' +
                '}';
    }
}
