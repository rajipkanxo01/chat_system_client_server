package client.view.mainView;

import client.core.ModelFactory;
import client.model.TicTacToeModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


import java.beans.PropertyChangeEvent;
import java.util.List;

public class MainViewModel {
    private ObservableList<String> allUsers;
    private ModelFactory modelFactory;
    private TicTacToeModel ticTacToeModel;


    private Listener listener = new Listener();

    public MainViewModel(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        ticTacToeModel = modelFactory.getTicTacToeModel();

        List<String> list = modelFactory.getLoginModel().getAllUsers();
        allUsers = FXCollections.observableArrayList(list);

        modelFactory.getLoginModel().addListener("userAdded", this::userAdded);
    }

    private void userAdded(PropertyChangeEvent event) {
        System.out.println("fired in main view model");
        Platform.runLater(() -> allUsers.add((String) event.getNewValue()));
    }

    public void sendGameRequest(String user) {
        modelFactory.getTicTacToeModel().sendGameRequest(user);
    }


    public ObservableList<String> getAllUsers() {
        return allUsers;
    }



    public Listener getListener() {
        return listener;
    }

    private class Listener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println(event.getSource());
            Button source = (Button) event.getSource();
            source.setDisable(true);
            source.setText("X");

            ticTacToeModel.sendButtonClicked (source);


        }

    }
}
