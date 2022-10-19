package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable {
    private ArrayList<User> users;

    public UserList() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public boolean doesUserExists(User user) {
        for (User value : users) {
            if (value.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getAllUsers () {
        return users;
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public User getUser (int index) {
        return users.get(index);
    }
}
