package client.core;

import client.model.ChatModel;
import client.model.ChatModelImp;
import client.model.LoginModel;
import client.model.LoginModelImp;
import client.view.mainView.MainViewModel;

public class ModelFactory {
    private ClientFactory clientFactory;
    private LoginModel loginModel;
    private ChatModel chatModel;

    public ModelFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public LoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModelImp(clientFactory.getClient());
        }
        return loginModel;
    }

    public ChatModel getChatModel() {
        if (chatModel == null) {
            chatModel = new ChatModelImp(clientFactory.getClient());
        }
        return chatModel;
    }


}
