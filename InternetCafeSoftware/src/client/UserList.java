package client;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class UserList {
    private HashMap<String, User> userList;

    public void add(String username, User user) {
        userList.put(username, user);
    }

    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream("userlist.db");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userList);
            fos.close();
            oos.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void load() {
        try {
            FileInputStream fis = new FileInputStream("userlist.db");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public User get(String username) {
        return userList.get(username);
    }

    public boolean contains(String username) {
        return userList.containsKey(username);
    }

}
