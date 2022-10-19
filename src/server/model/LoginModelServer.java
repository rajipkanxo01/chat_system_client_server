package server.model;

import shared.User;
import shared.utils.Subject;

public interface LoginModelServer extends Subject {
    void addUser (User user);
    boolean checkSignUp(String username);
    boolean checkLogIn(User user);
    User getUser ();

}
