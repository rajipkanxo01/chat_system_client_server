package client.networking;

import shared.User;
import shared.transferObjects.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient implements Client {
    private PropertyChangeSupport support;

    public SocketClient() {
        support = new PropertyChangeSupport(this);
    }

    public void startClient() {
        try {
            Socket socket = new Socket("localhost", 2001);
            System.out.println("Client started");
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            new Thread(() -> listenToServer(outToServer, inFromServer)).start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // It sends a request to the server to start listening to it, and then it listens to the server and fires a property
    //  change event for each request it receives
    private void listenToServer(ObjectOutputStream outToServer, ObjectInputStream inFromServer) {
        try {
            outToServer.writeObject(new Request("Listener", null));
            while (true) {
                Request request = (Request) inFromServer.readObject();
                support.firePropertyChange(request.getType(), null, request.getArg());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        // sends request to server to add user to the list of all users
        try {
            Request response = request("addUser", user);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean checkSignUp(String username) {
        boolean b;
        try {
            Request response = request("checkSignUp", username);
            b = (boolean) response.getArg();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return b;
    }

    @Override
    public boolean checkSignIn(User user) {
        boolean b;
        try {
            Request response = request("checkLogIn", user);
            b = (boolean) response.getArg();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return b;
    }


    private Request request(String type, Object arg) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 2001);

        ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

        outToServer.writeObject(new Request(type, arg));

        return (Request) inFromServer.readObject();
    }


    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
