package client.model;

import client.networking.Client;
import shared.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginModelImp implements LoginModel {
    private PropertyChangeSupport support;
    private Client client;

    public LoginModelImp(Client client) {
        support = new PropertyChangeSupport(this);
        this.client = client;
        client.addListener("userAdded", this::userAdded);
//        client.addListener("userRemoved", this::userRemoved);
    }

//    private void userRemoved(PropertyChangeEvent event) {
//        support.firePropertyChange("userRemoved", null, event.getNewValue());
//    }

    private void userAdded(PropertyChangeEvent event) {
        support.firePropertyChange("userAdded", null, event.getNewValue());
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
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
