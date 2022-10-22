package client.model;

import client.networking.Client;
import shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ChatModelImp implements ChatModel {
    private PropertyChangeSupport support;
    private Client client;

    public ChatModelImp(Client client) {
        this.client = client;
        support = new PropertyChangeSupport(this);
        client.addListener("userLoggedIn", this::userLoggedIn);
        client.addListener("newMessage",this::newMessage);
    }

    private void newMessage(PropertyChangeEvent event) {
        support.firePropertyChange(event);
        System.out.println(event.getPropertyName() + " : fired in chat model imp");
    }

    private void userLoggedIn(PropertyChangeEvent event) {
        support.firePropertyChange(event);
    }

    @Override
    public void sendMessage(Message message) {
        client.sendMessage(message);
    }

    @Override
    public List<Message> getAllPreviousMessages() {
        return null;
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
