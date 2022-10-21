package client.model;

import client.networking.Client;
import javafx.scene.control.Button;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicTacToeModelImp implements TicTacToeModel {
    private PropertyChangeSupport support;
    private Client client;

    private boolean isPlayer1Turn = false;
    private boolean checkWinByPlayer1 = false;
    private boolean checkWinByPlayer2 = false;
    private boolean isGameTie = false;
    private boolean isMarkerCircle = false;


    public TicTacToeModelImp(Client client) {
        this.client = client;
        support = new PropertyChangeSupport(this);
//        client.addListener("userAdded", this::userAdded);
    }




    @Override
    public void sendGameRequest(String username) {
        client.sendGameRequest(username);
    }

    @Override
    public void changePlayerTurn() {
        client.changePlayerTurn();
    }

    @Override
    public boolean checkForWin() {
        return false;
    }

    @Override
    public void restartGame() {

    }

    @Override
    public void sendButtonClicked(Button source) {

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
