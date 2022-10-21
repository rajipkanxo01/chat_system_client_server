package client.view.mainView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;

public class MainViewController implements Controller {
    private ViewHandler vh;
    private ViewModelFactory vmf;
    @FXML
    private ListView<String> allUsersList;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label playingWith;

    private ArrayList<Button> buttons;


    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vmf = vmf;


        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        allUsersList.setItems(vmf.getMainVM().getAllUsers());


        for (Button button : buttons) {
            button.setOnAction(vmf.getMainVM().getListener());
        }
    }


    public void onSelectUser(ActionEvent actionEvent) {
        vmf.getMainVM().sendGameRequest(allUsersList.getSelectionModel().getSelectedItem());
    }


}


