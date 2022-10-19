package client.view.mainView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainViewController implements Controller {
    private ViewHandler vh;
    private ViewModelFactory vmf;
    @FXML
    private ListView<String> allUsersList;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vmf = vmf;

        allUsersList.setItems(vmf.getMainVM().getAllUsers());
    }
}
