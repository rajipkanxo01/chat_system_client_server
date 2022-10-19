package server.model;

import client.networking.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class TicTacToeServerImplServer implements TicTacToeServer {
    private PropertyChangeSupport support;
    private Client client;


    public TicTacToeServerImplServer(Client client) {
        this.client = client;
        support = new PropertyChangeSupport(this);

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
