package client.core;

import client.model.LoginModel;
import client.model.LoginModelImp;

public class ModelFactory {
    private ClientFactory clientFactory;
    private LoginModel loginModel;

    public ModelFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public LoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModelImp(clientFactory.getClient());
        }
        return loginModel;
    }


}
