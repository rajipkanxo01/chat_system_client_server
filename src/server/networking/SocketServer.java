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
            List<ServerSocketHandler> handlers = new ArrayList<>();

            while (true) {
                Socket socket = welcomeSocket.accept();
                System.out.println("Client accepted");
                ServerSocketHandler handler = new ServerSocketHandler(socket, loginModelServer);
                handlers.add(handler);
                System.out.println("handlers: " + handlers.size());
                new Thread(handler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
