package client.model;

import shared.utils.Subject;

import java.util.List;

public interface TicTacToeModel extends Subject {
    List<String> getAllUsers();
}
