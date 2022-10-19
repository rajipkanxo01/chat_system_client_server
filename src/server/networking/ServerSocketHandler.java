package server.networking;

import server.model.LoginModelServer;
import shared.User;
import shared.transferObjects.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerSocketHandler implements Runnable {
    private Socket socket;
    private LoginModelServer loginModelServer;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    public ServerSocketHandler(Socket socket, LoginModelServer loginModelServer) {
        this.socket = socket;
        this.loginModelServer = loginModelServer;

        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            Request readObject = (Request) inFromClient.readObject();

            if ("Listener".equals(readObject.getType())) {
                loginModelServer.addListener("NewListener", this::onNewListener);
            } else if
            ("checkSignUp".equals(readObject.getType())) {
                boolean status = loginModelServer.checkSignUp((String) readObject.getArg());
                outToClient.writeObject(new Request("checkSignUp", status));
            } else if
            ("addUser".equals(readObject.getType())) {
                loginModelServer.addUser((User) readObject.getArg());
                outToClient.writeObject(new Request("addUser", null));
            } else if
            ("checkLogIn".equals(readObject.getType())) {
                boolean status = loginModelServer.checkLogIn((User) readObject.getArg());
                outToClient.writeObject(new Request("checkLogIn", status));
            } else if ("getAllUsers".equals(readObject.getType())) {
                List<String> allUsers = loginModelServer.getAllUsers();
                outToClient.writeObject(new Request("getAllUsers", allUsers));
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void onNewListener(PropertyChangeEvent event) {

    }
}
