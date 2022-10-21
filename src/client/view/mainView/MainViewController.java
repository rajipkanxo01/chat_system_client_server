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
    private Label user;



    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vmf = vmf;

    }


    public void onSelectUser(ActionEvent actionEvent) {
    }


}


