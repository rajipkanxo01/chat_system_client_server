package shared;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;
    private String username;

    public Message(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String toString () {
        return username + ": " + message;
    }
}
