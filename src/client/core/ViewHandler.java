package client.core;

import client.view.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private Scene logInScene;
    private Scene signUpScene;
    private Scene mainViewScene;
    private ViewModelFactory vmf;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public void start() {
        stage = new Stage();
        openLogInView();
    }

    public void openSignUpView() {
        if (signUpScene == null) {
            try {
                Parent root = loadFXML("../view/signUpView/SignUpView.fxml");
                stage.setTitle("Sign Up");
                stage.setResizable(false);
                signUpScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setScene(signUpScene);
        stage.show();
    }

    public void openLogInView() {
        if (logInScene == null) {
            try {
                Parent root = loadFXML("../view/logInView/LogInView.fxml");
                stage.setTitle("Log in");
                stage.setResizable(false);
                logInScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setScene(logInScene);
        stage.show();
    }

    public void openMainView () {
        if (mainViewScene == null) {
            try {
                Parent root = loadFXML("../view/mainView/MainView.fxml");
                stage.setTitle("Main View");
                stage.setResizable(false);
                mainViewScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setScene(mainViewScene);
        stage.show();
    }


    private Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        Controller ctrl = loader.getController();
        ctrl.init(this, vmf);
        return root;
    }
}
