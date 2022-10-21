package client.model;

import javafx.scene.control.Button;
import shared.User;
import shared.utils.Subject;

import java.util.List;

public interface TicTacToeModel extends Subject {
    void sendGameRequest(String username);

    void changePlayerTurn ();
    boolean checkForWin ();

    void restartGame ();

    void sendButtonClicked(Button source);
}
