package server.model;

import shared.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ChatModelServerImp implements ChatModelServer {
    private PropertyChangeSupport support;
    private List<Message> messages;

    public ChatModelServerImp() {
        support = new PropertyChangeSupport(this);
        messages = new ArrayList<>();
    }



    @Override
    public void addMessage(Message message) {
        messages.add(message);
        support.firePropertyChange("newMessage",null,message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messages;
    }

    @Override
    public List<Message> getPreviousMessages() {
        return messages;
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
