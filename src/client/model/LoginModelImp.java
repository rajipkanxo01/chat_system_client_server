package client.model;

import client.networking.Client;
import shared.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class LoginModelImp implements LoginModel {
    private PropertyChangeSupport support;
    private Client client;
    private User user;

    public LoginModelImp(Client client) {
        support = new PropertyChangeSupport(this);
        this.client = client;
//        client.startClient();
    }

    @Override
    public void addUser(String username, String password) {
        // calls add user method from client
        client.addUser(new User(username, password));
    }

    @Override
    public boolean checkSignUp(String username) {
        return client.checkSignUp(username);
    }

    @Override
    public boolean checkLogIn(User user) {
        boolean status = client.checkSignIn(user);
        if (status) {
            this.user = user;
        }
        return status;
    }


    public User getUser() {
        return user;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }


    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
