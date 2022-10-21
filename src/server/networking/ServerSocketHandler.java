package server.networking;

import server.model.LoginModelServer;
import shared.User;
import shared.transferObjects.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
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
    private PropertyChangeSupport support;
    private ConnectionPool cp;
    private Request readObject;

    public ServerSocketHandler(Socket socket, LoginModelServer loginModelServer, ConnectionPool cp) {
        this.socket = socket;
        this.loginModelServer = loginModelServer;
        this.cp = cp;

        support = new PropertyChangeSupport(this);

        try {
            inFromClient = new ObjectInputStream(socket.getInputStream());
            outToClient = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {

            readObject = (Request) inFromClient.readObject();

            if ("Listener".equals(readObject.getType())) {
//                ticTacToeServer.addListener("NewUserAdded", this::onNewUserAdded);
            } else if
            ("checkSignUp".equals(readObject.getType())) {
                boolean status = loginModelServer.checkSignUp((String) readObject.getArg());
                System.out.println("Sign Up Status: " + status);
                outToClient.writeObject(new Request("checkSignUp", status));
            } else if
            ("addUser".equals(readObject.getType())) {
                loginModelServer.addUser((User) readObject.getArg());
                outToClient.writeObject(new Request("addUser", null));
            } else if
            ("checkLogIn".equals(readObject.getType())) {
                boolean status = loginModelServer.checkLogIn((User) readObject.getArg());
                System.out.println("Log In Status: " + status);
                outToClient.writeObject(new Request("checkLogIn", status));
                cp.addConnection(this);
//                if (status) {
//                    cp.broadCastUsername(((User) readObject.getArg()).getUsername());
//                }

            } else if
            ("getAllUsers".equals(readObject.getType())) {
                List<String> allUsers = loginModelServer.getAllUsers();
                outToClient.writeObject(new Request("getAllUsers", allUsers));
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void onNewUserAdded(PropertyChangeEvent event) {
        try {
            outToClient.writeObject(new Request(event.getPropertyName(), event.getNewValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendUsername(String username) {
        try {
            outToClient.writeObject(new Request("userAdded", username));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return ((User) readObject.getArg()).getUsername();
    }
}
