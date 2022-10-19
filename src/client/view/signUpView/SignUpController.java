package client.view.signUpView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.Controller;
import client.view.logInView.LogInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController implements Controller {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField repeatPassword;
    @FXML
    private Label errorLabel;

    private SignUpViewModel signUpViewModel;

    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.signUpViewModel = vmf.getSignupVM();
        username.textProperty().bindBidirectional(signUpViewModel.usernameProperty());
        password.textProperty().bindBidirectional(signUpViewModel.passwordProperty());
        repeatPassword.textProperty().bindBidirectional(signUpViewModel.repeatPasswordProperty());
        errorLabel.textProperty().bind(signUpViewModel.errorLabelProperty());
    }

    public void onSignUpButton(ActionEvent actionEvent) {
        signUpViewModel.addUser(username.getText(), password.getText(), repeatPassword.getText());
    }

    public void onSignInButton(ActionEvent actionEvent) {
        vh.openLogInView();
        signUpViewModel.clearAll();
    }

}
