package server.networking;

import server.model.LoginModelServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer {
    private LoginModelServer loginModelServer;

    public SocketServer(LoginModelServer loginModelServer) {
        this.loginModelServer = loginModelServer;
    }

    public void startServer() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(2001);
            System.out.println("Server Started...");
            ConnectionPool connectionPool = new ConnectionPool();

            while (true) {
                Socket socket = welcomeSocket.accept();
                ServerSocketHandler handler = new ServerSocketHandler(socket, loginModelServer, connectionPool);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
