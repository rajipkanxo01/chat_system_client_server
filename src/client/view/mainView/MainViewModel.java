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


    public MainViewModel(ModelFactory modelFactory) {
        this.chatModel = modelFactory.getChatModel();
        this.loginModel = modelFactory.getLoginModel();
        messageText = new SimpleStringProperty();

        List<String> previousUser = chatModel.getAllUsers();
        allUsers = FXCollections.observableArrayList(previousUser);

        allMessages = FXCollections.observableArrayList();

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


    public ObservableList<String> getAllUsers() {
        return allUsers;
    }

    public ObservableList<Message> getAllMessages() {
        return allMessages;
    }

    public void sendGroupMessage() {
        System.out.println(messageText.getValue() + ":main view model");
        chatModel.sendMessage(new Message(messageText.getValue(), loginModel.getUser().getUsername()));
    }


    public SimpleStringProperty messageTextProperty() {
        return messageText;
    }
}
