package client.core;

import client.model.LoginModel;
import client.model.LoginModelImp;
import client.model.TicTacToeModel;
import client.model.TicTacToeModelImp;

public class ModelFactory {
    private ClientFactory clientFactory;
    private LoginModel loginModel;
    private TicTacToeModel ticTacToeModel;

    public ModelFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public LoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModelImp(clientFactory.getClient());
        }
        return loginModel;
    }

    public TicTacToeModel getTicTacToeModel () {
        if (ticTacToeModel == null) {
            ticTacToeModel = new TicTacToeModelImp(clientFactory.getClient());
        }
        return ticTacToeModel;
    }


}
