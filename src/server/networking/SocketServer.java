package server.networking;

import server.model.ChatModelServer;
import server.model.LoginModelServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private LoginModelServer loginModelServer;
    private ChatModelServer chatModelServer;

    public SocketServer(LoginModelServer loginModelServer,ChatModelServer chatModelServer) {
        this.loginModelServer = loginModelServer;
        this.chatModelServer = chatModelServer;
    }

    public void startServer() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(2001);
            System.out.println("Server Started...");
            ConnectionPool connectionPool = new ConnectionPool();

            while (true) {
                Socket socket = welcomeSocket.accept();
                ServerSocketHandler handler = new ServerSocketHandler(socket, loginModelServer, chatModelServer, connectionPool);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
