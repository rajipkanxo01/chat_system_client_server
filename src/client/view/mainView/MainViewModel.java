package client.view.mainView;

import client.core.ModelFactory;
import client.model.ChatModel;
import client.model.LoginModel;
import javafx.application.Platform;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Message;
import shared.User;


import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel {
    private ChatModel chatModel;
    private LoginModel loginModel;
    private ObservableList<String> allUsers;
    private ObservableList<Message> allMessages;
    private SimpleStringProperty messageText;
    private SimpleStringProperty currentUser;


    public MainViewModel(ChatModel chatModel, LoginModel loginModel) {
        this.chatModel = chatModel;
        this.loginModel = loginModel;
        messageText = new SimpleStringProperty();
        currentUser = new SimpleStringProperty();

        currentUser.setValue(loginModel.getUser().getUsername());

        List<String> previousUser = chatModel.getAllUsers();
        allUsers = FXCollections.observableArrayList(previousUser);

        allMessages = FXCollections.observableArrayList(chatModel.getAllPreviousMessages());

        chatModel.addListener("userLoggedIn", this::userAdded);
        chatModel.addListener("newMessage", this::newMessage);
    }

    private void newMessage(PropertyChangeEvent event) {
        System.out.println(event.getNewValue());
        Platform.runLater(() -> allMessages.add(((Message) event.getNewValue())));
    }


    //showing user list and updating
    private void userAdded(PropertyChangeEvent event) {
        Platform.runLater(() -> allUsers.add(((User) event.getNewValue()).getUsername()));
    }

    public void sendGroupMessage() {
        chatModel.sendMessage(new Message(messageText.getValue(), currentUser.getValue()));
    }


    public ObservableList<String> getAllUsers() {
        return allUsers;
    }

    public ObservableList<Message> getAllMessages() {
        return allMessages;
    }

    public SimpleStringProperty messageTextProperty() {
        return messageText;
    }

    public SimpleStringProperty currentUserProperty() {
        return currentUser;
    }
}
