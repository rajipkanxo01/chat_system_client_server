package server.model;

import shared.User;
import shared.utils.Subject;

import java.util.List;

public interface TicTacToeServer extends Subject {
//    void getGameRequest(User user);

    void firstTurn();

    void checkForWinCondition();

    void xWins(int a, int b, int c);

    void oWins(int a, int b, int c);
}
