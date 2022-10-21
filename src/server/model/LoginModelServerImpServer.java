package server.model;

import client.networking.Client;
import shared.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class LoginModelServerImpServer implements LoginModelServer {
    private PropertyChangeSupport support;

    private List<User> userList;

    public LoginModelServerImpServer() {
        support = new PropertyChangeSupport(this);
        userList = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
        System.out.println(userList.size());
    }

    @Override
    public boolean checkSignUp(String username) {
        boolean status = true;
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                status = false;
                break;
            }
        }
        return status;
    }

    public List<String> getAllUsers () {
        List<String> username = new ArrayList<>();
        for (User user: userList) {
            username.add(user.getUsername());
        }
        return username;
    }

    @Override
    public boolean checkLogIn(User user) {
        if (userList.contains(user)) {
            support.firePropertyChange("NewListener",null,user);
        }
        return userList.contains(user);
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
