package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelServerImp implements ChatModelServer {
    private PropertyChangeSupport support;

    public ChatModelServerImp() {
        support = new PropertyChangeSupport(this);
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
