package client.model;

import shared.Message;
import shared.utils.Subject;

import java.util.List;

public interface ChatModel extends Subject {

    List<String> getAllUsers();

    void sendMessage(Message message);

    List<Message> getAllPreviousMessages();
}
