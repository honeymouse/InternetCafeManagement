package client;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class UserList implements Serializable {
    private static HashMap<String, User> userList;

    public UserList() {
        userList = new HashMap<>();
        load();
    }

    public static void UPDATEUSER(User user) {
        userList.put(user.getName(), user);
        UserList userList = new UserList();
        userList.save();
    }

    public void add(String username, User user) {
        System.out.println("added user");
        userList.put(username, user);
        save();
        load();
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
            JOptionPane.showMessageDialog(null, "load: " + e.getMessage());
        }
    }

    public User get(String username) {
        return userList.get(username);
    }

    public boolean contains(String username) {
        System.out.println("check to see if it contains username");
        System.out.println(userList.containsValue(username));
        return userList.containsKey(username);
    }

}
