package client.view.mainView;

import client.core.ModelFactory;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.User;


import java.beans.PropertyChangeEvent;
import java.util.List;

public class MainViewModel {
    private ObservableList<String> allUsers;
    private ModelFactory modelFactory;


    public MainViewModel(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;

        List<String> list = modelFactory.getLoginModel().getAllUsers();
        allUsers = FXCollections.observableArrayList(list);

        modelFactory.getLoginModel().addListener("userLoggedIn", this::userAdded);
    }

    private void userAdded(PropertyChangeEvent event) {
        System.out.println("User list size:" + allUsers.size());
        Platform.runLater(() -> allUsers.add(((User) event.getNewValue()).getUsername()));
        System.out.println("User list size:" + allUsers.size());
    }


    public ObservableList<String> getAllUsers() {
        return allUsers;
    }


}
