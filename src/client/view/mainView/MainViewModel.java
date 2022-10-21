package client.view.mainView;

import client.core.ModelFactory;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.beans.PropertyChangeEvent;
import java.util.List;

public class MainViewModel {
    private ObservableList<String> allUsers;
    private ModelFactory modelFactory;




    public MainViewModel(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;

        List<String> list = modelFactory.getLoginModel().getAllUsers();
        allUsers = FXCollections.observableArrayList(list);

        modelFactory.getLoginModel().addListener("NewUserAdded", this::userAdded);

//        user.setValue(modelFactory.getLoginModel().getUser().getUsername());
    }

    private void userAdded(PropertyChangeEvent event) {
        System.out.println("fired in main view model");
        Platform.runLater(() -> allUsers.add((String) event.getNewValue()));
    }


    public ObservableList<String> getAllUsers() {
        return allUsers;
    }




}
