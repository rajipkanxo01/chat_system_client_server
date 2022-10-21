package client.model;

import shared.User;
import shared.utils.Subject;

import java.util.List;

public interface LoginModel extends Subject {
    void addUser(String username, String password);

    boolean checkSignUp(String username);

    boolean checkLogIn(User user);

    List<String> getAllUsers();


}
