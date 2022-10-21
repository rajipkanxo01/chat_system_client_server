package server.model;

import shared.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class LoginModelServerImp implements LoginModelServer {
    private PropertyChangeSupport support;

    private List<User> userList;

    public LoginModelServerImp() {
        support = new PropertyChangeSupport(this);
        userList = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
//        support.firePropertyChange("NewUserAdded",null,user);
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

    public List<String> getAllUsers() {
        List<String> username = new ArrayList<>();
        for (User user : userList) {
            username.add(user.getUsername());
        }
        return username;
    }

    @Override
    public boolean checkLogIn(User user) {
        boolean status = false;
        for (User account : userList
        ) {
            if (account.equals(user)) {
                status = true;
               support.firePropertyChange("userLoggedIn",null,user);
            }
        }
        return status;
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
