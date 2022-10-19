package server;

import server.model.LoginModelServerImpServer;
import server.networking.SocketServer;

public class RunServer {
    public static void main(String[] args) {
        SocketServer ss = new SocketServer(new LoginModelServerImpServer());
        ss.startServer();
    }
}
