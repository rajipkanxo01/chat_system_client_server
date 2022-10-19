package client.model;

import client.networking.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class TicTacToeModelImp implements TicTacToeModel {
    private PropertyChangeSupport support;
    private Client client;


    public TicTacToeModelImp(Client client) {
        this.client = client;
        support = new PropertyChangeSupport(this);
        client.addListener("userAdded", this::userAdded);

    }

    private void userAdded(PropertyChangeEvent event) {
        System.out.println("fired from tic tac toe implementation");
        support.firePropertyChange("userAdded", null, event.getNewValue());
    }

    @Override
    public List<String> getAllUsers() {
        return client.getAllUsers();
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName,listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName,listener);
    }
}
