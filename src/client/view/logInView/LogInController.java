package client.view.logInView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LogInController implements Controller {


    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Label errorLabel;

    private ViewHandler vh;
    private LogInViewModel logInViewModel;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.logInViewModel = vmf.getLoginVM();
        username.textProperty().bindBidirectional(logInViewModel.usernameProperty());
        password.textProperty().bindBidirectional(logInViewModel.passwordProperty());
        errorLabel.textProperty().bind(logInViewModel.errorLabelProperty());
    }

    public void onSignInButton(ActionEvent actionEvent) {
        if (logInViewModel.logInUser()) {
            vh.openMainView();
        }
    }

    public void onSignUpButton(ActionEvent actionEvent) {
        vh.openSignUpView();
        logInViewModel.clearAll();
    }


}
