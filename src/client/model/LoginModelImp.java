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

    public LoginModelImp(Client client) {
        support = new PropertyChangeSupport(this);
        this.client = client;
        client.startClient();
        client.addListener("userLoggedIn", this::userLoggedIn);
    }


    private void userLoggedIn(PropertyChangeEvent event) {
        support.firePropertyChange(event);
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
        return client.checkSignIn(user);
    }

    @Override
    public List<String> getAllUsers() {
        return client.getAllUsers();
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
