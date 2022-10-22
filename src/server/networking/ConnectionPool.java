package server.networking;


import shared.Message;

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


    public int size() {
        return handlers.size();
    }


    public void broadCastMessage(Message message) {
        for (ServerSocketHandler socketHandler : handlers) {
            socketHandler.sendMessage(message);
        }
    }
}
