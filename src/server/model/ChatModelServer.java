package server.model;

import shared.Message;
import shared.utils.Subject;

import java.util.List;

public interface ChatModelServer extends Subject {
    void addMessage (Message message);
    List<Message> getAllMessages ();

    List<Message> getPreviousMessages();
}
