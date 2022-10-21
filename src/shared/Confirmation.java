package shared;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import shared.utils.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Optional;

public abstract class Confirmation implements Serializable, Subject {
    private PropertyChangeSupport support;

    public Confirmation() {
        support = new PropertyChangeSupport(this);
    }

    public static boolean showAlert(String text) {
        boolean status = true;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Request");
        alert.setHeaderText(text);
        alert.setContentText("is challenging you for a game");
        Optional<ButtonType> option = alert.showAndWait();
        alert.showAndWait();

        if (option.get() != ButtonType.OK) {
            status = false;
        }
        return status;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
