package server.model;

import client.networking.Client;
import shared.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Random;

public class TicTacToeServerImplServer implements TicTacToeServer {
    private PropertyChangeSupport support;
    private Client client;
    private boolean player1_turn;

    private Random random = new Random();


    public TicTacToeServerImplServer(Client client) {
        this.client = client;
        support = new PropertyChangeSupport(this);

    }


//    @Override
//    public void getGameRequest(User user) {
//
//    }

    @Override
    public void firstTurn() {

    }

    @Override
    public void checkForWinCondition() {

    }

    @Override
    public void xWins(int a, int b, int c) {

    }

    @Override
    public void oWins(int a, int b, int c) {

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
