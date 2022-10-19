package client.networking;

import shared.User;
import shared.utils.Subject;

public interface Client extends Subject {
    void addUser (User user);
    boolean checkSignUp(String username);
    boolean checkSignIn(User user);

    void startClient();
}
