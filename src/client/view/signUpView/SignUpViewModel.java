package client.view.signUpView;

import client.model.LoginModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.User;

public class SignUpViewModel {
    private LoginModel loginModel;
    private StringProperty username, password, repeatPassword, errorLabel;

    public SignUpViewModel(LoginModel loginModel) {
        this.loginModel = loginModel;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        repeatPassword = new SimpleStringProperty();
        errorLabel = new SimpleStringProperty();

    }

    public void addUser(String username, String password, String repeat) {
        // checks if all fields are empty or not, also if username already exists
        if (username != null && password != null && repeat != null && loginModel.checkSignUp(username)) {
            // calls add user method on login model, which sends request to the server
            loginModel.addUser(username, password);
            System.out.println("Created");
            errorLabel.setValue("Account Created");
            clearAll();
        } else if (username == null || password == null) {
            errorLabel.setValue("Fields empty");
            clearAll();
        } else {
            if (!password.equals(repeat)) {
                errorLabel.setValue("Password does not match ");
                clearAll();
            } else if (!loginModel.checkSignUp(username)) {
                errorLabel.setValue("Username already exists!!");
                clearAll();
            }
        }
    }

    public void clearAll() {
        new Thread(() -> { // Lambda Expression
            try {
                Thread.sleep(1000);
                username.setValue("");
                password.setValue("");
                repeatPassword.setValue("");
                errorLabel.setValue("");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }).start();
    }


    public StringProperty usernameProperty() {
        return username;
    }


    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty repeatPasswordProperty() {
        return repeatPassword;
    }


    public StringProperty errorLabelProperty() {
        return errorLabel;
    }


}
