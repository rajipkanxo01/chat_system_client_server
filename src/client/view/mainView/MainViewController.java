package client.view.mainView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.Controller;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import shared.Message;


public class MainViewController implements Controller {
    private ViewHandler vh;
    private ViewModelFactory vmf;
    @FXML
    private ListView<String> allUsersList;
    @FXML
    private Label user;
    @FXML
    private TextField sendMessageField;
    @FXML
    private ListView<Message> allMessagesList;


    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vmf = vmf;

        allUsersList.setItems(vmf.getMainVM().getAllUsers());
        allMessagesList.setItems(vmf.getMainVM().getAllMessages());

        sendMessageField.textProperty().bindBidirectional(vmf.getMainVM().messageTextProperty());

    }


    public void onSelectUser(ActionEvent actionEvent) {

    }


    public void onSendMessage(ActionEvent event) {
        vmf.getMainVM().sendGroupMessage();
    }

}


