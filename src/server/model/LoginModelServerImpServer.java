package server.model;

import client.networking.Client;
import shared.User;
import shared.UserList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class LoginModelServerImpServer implements LoginModelServer {
    private PropertyChangeSupport support;

    private UserList userList;

    public LoginModelServerImpServer() {
        support = new PropertyChangeSupport(this);
        userList = new UserList();
    }

    @Override
    public void addUser(User user) {
        userList.addUser(user);
        System.out.println(userList.getNumberOfUsers());
    }

    @Override
    public boolean checkSignUp(String username) {
        boolean status = true;
        for (int i = 0; i < userList.getNumberOfUsers(); i++) {
            if (userList.getUser(i).getUsername().equals(username)) {
                status = false;
                break;
            }
        }
        return status;
    }

    public List<String> getAllUsers () {
        return userList.getAllUsername();
    }

    @Override
    public boolean checkLogIn(User user) {
        return userList.doesUserExists(user);
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
