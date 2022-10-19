package client.model;

import shared.User;
import shared.utils.Subject;

public interface LoginModel extends Subject {
    void addUser (String username, String password);
    boolean checkSignUp (String username);
    boolean checkLogIn (User user);
    User getUser ();

}
