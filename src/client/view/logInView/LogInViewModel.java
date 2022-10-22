package client.view.logInView;

import client.core.ModelFactory;
import client.model.LoginModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.User;

public class LogInViewModel {
    private LoginModel loginModel;

    private StringProperty username, password, errorLabel;


    public LogInViewModel(ModelFactory modelFactory) {
        this.loginModel = modelFactory.getLoginModel();
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        errorLabel = new SimpleStringProperty();
    }

    public boolean logInUser() {
        User user = new User(username.get(), password.get());

        if (!username.get().equals("") && !password.get().equals("")) {
            if (loginModel.checkLogIn(user)) {
                System.out.println("User exists, can login");
                clearAll();
                return true;
            } else {
                errorLabel.setValue("Account doesn't exists. Create one");
                clearAll();
                return false;
            }
        } else {
            errorLabel.setValue("Username or password field empty!!");
            clearAll();
            return false;
        }
    }

    public void clearAll() {
        new Thread(() -> { // Lambda Expression
            try {
                Thread.sleep(1000);
                username.setValue("");
                password.setValue("");
                Platform.runLater(() -> errorLabel.setValue(""));
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


    public StringProperty errorLabelProperty() {
        return errorLabel;
    }
}
