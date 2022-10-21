package client.view.mainView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;

public class MainViewController implements Controller {
    private ViewHandler vh;
    private ViewModelFactory vmf;
    @FXML
    private ListView<String> allUsersList;
    @FXML
    private Label user;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private VBox messageVBox;
    @FXML
    private Button onSendClick;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField sendMessageField;


    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vmf = vmf;

        allUsersList.setItems(vmf.getMainVM().getAllUsers());


        messageVBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                scrollPane.setVvalue((Double) newValue);
            }
        });

    }


    public void onSelectUser(ActionEvent actionEvent) {
    }


}


