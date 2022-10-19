package client.networking;

import shared.User;
import shared.utils.Subject;

import java.util.List;

public interface Client extends Subject {
    void addUser (User user);
    boolean checkSignUp(String username);
    boolean checkSignIn(User user);

   List<String> getAllUsers ();

    void startClient();
}
