package client.view.mainView;

import client.core.ModelFactory;
import client.model.TicTacToeModel;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class MainViewModel {
    private ObservableList<String> allUsers;
    private ModelFactory modelFactory;

    public MainViewModel(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;

        List<String> list = modelFactory.getTicTacToeModel().getAllUsers();
        allUsers = FXCollections.observableArrayList(list);

        modelFactory.getTicTacToeModel().addListener("userAdded", this::userAdded);
    }

    private void userAdded(PropertyChangeEvent event) {
        Platform.runLater(() -> allUsers.add((String) event.getNewValue()));
    }


    public ObservableList<String> getAllUsers() {
        return allUsers;
    }
}
