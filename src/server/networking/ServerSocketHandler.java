package server.networking;

import server.model.ChatModelServer;
import server.model.LoginModelServer;
import shared.Message;
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
    private ChatModelServer chatModelServer;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private PropertyChangeSupport support;
    private ConnectionPool cp;
    private Request readObject;
    private User user;

    public ServerSocketHandler(Socket socket, LoginModelServer loginModelServer, ChatModelServer chatModelServer, ConnectionPool cp) {
        this.socket = socket;
        this.loginModelServer = loginModelServer;
        this.chatModelServer = chatModelServer;
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

            switch (readObject.getType()) {
                case "Listener" -> {
                    user = (User) readObject.getArg();
                    loginModelServer.addListener("userLoggedIn", this::onUserLoggedIn);
                    chatModelServer.addListener("newMessage", this::onNewMessage);
                }
                case "checkSignUp" -> {
                    boolean status = loginModelServer.checkSignUp((String) readObject.getArg());
                    System.out.println("Sign Up Status: " + status);
                    outToClient.writeObject(new Request("checkSignUp", status));
                }
                case "addUser" -> {
                    loginModelServer.addUser((User) readObject.getArg());
                    outToClient.writeObject(new Request("addUser", null));
                }
                case "checkLogIn" -> {
                    boolean status = loginModelServer.checkLogIn((User) readObject.getArg());
                    outToClient.writeObject(new Request("checkLogIn", status));
                    cp.addConnection(this);
                }
                case "getAllUsers" -> {
                    List<String> allUsers = loginModelServer.getAllUsers();
                    outToClient.writeObject(new Request("getAllUsers", allUsers));
                }
                case "sendMessage" -> {
                    chatModelServer.addMessage((Message) readObject.getArg());
                    System.out.println(user + ": server socket");
                    outToClient.writeObject(new Request("newMessage", null));
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void onNewMessage(PropertyChangeEvent event) {
        try {
            outToClient.writeObject(new Request(event.getPropertyName(), event.getNewValue()));
            System.out.println(event.getPropertyName() + ": " + support.getPropertyChangeListeners().length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onUserLoggedIn(PropertyChangeEvent event) {
        try {
            outToClient.writeObject(new Request(event.getPropertyName(), event.getNewValue()));
            System.out.println(event.getPropertyName() + ": " + support.getPropertyChangeListeners().length);
        } catch (IOException e) {
            throw new RuntimeException("Cannot log in to the system");
        }
    }


    public String getUsername() {
        return ((User) readObject.getArg()).getUsername();
    }

    public void sendMessage(Message message) {
        System.out.println(message);
        try {
            outToClient.writeObject(new Request("getMessage", message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
