package server;

import server.model.ChatModelServerImp;
import server.model.LoginModelServerImp;
import server.networking.SocketServer;

public class RunServer {
    public static void main(String[] args) {
        SocketServer ss = new SocketServer(new LoginModelServerImp(),new ChatModelServerImp());
        ss.startServer();
    }
}
