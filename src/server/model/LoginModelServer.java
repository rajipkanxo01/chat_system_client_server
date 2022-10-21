package server.model;

import shared.User;
import shared.utils.Subject;

import java.util.List;

public interface LoginModelServer extends Subject {
    void addUser (User user);
    boolean checkSignUp(String username);
    boolean checkLogIn(User user);

   List<String> getAllUsers ();



}
