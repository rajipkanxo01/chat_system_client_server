package shared;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + username + " Password: " + password;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof User user)) {
            return false;
        }
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }
}
