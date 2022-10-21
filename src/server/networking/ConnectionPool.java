package server.networking;


import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private List<ServerSocketHandler> handlers = new ArrayList<>();


    public void addConnection(ServerSocketHandler socketHandler) {

        handlers.add(socketHandler);
    }

    public void removeConnection(ServerSocketHandler socketHandler) {
        handlers.remove(socketHandler);
    }

    public void broadCastUsername(String username) {
        for (ServerSocketHandler socketHandler : handlers) {
            socketHandler.sendUsername(username);
        }
    }

    public int size() {
        return handlers.size();
    }

    public void broadCastGameRequest(String username) {
        for (ServerSocketHandler socketHandler : handlers) {
            if (socketHandler.getUsername().equals(username)) {
//                Confirmation.showAlert("Player");
                System.out.println("game request is send from cp" + username);

            }
        }
    }

}