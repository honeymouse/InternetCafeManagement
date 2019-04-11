package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel loginPanel;
    private JPanel buttonPanel;
    private UserList userList;

    public LoginScreen() {
        super("Login");
        setLayout(new BorderLayout());

        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // username
        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(15);
        loginPanel.add(usernameLabel, BorderLayout.WEST);
        loginPanel.add(usernameField, BorderLayout.CENTER);

        // password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        passwordField.addKeyListener(new KeyListener() {
                                         @Override
                                         public void keyTyped(KeyEvent e) {

                                         }

                                         @Override
                                         public void keyPressed(KeyEvent e) {
                                             if (e.getKeyCode() == KeyEvent.VK_ENTER) login();
                                         }

                                         @Override
                                         public void keyReleased(KeyEvent e) {

                                         }
                                     });
        loginPanel.add(passwordLabel, BorderLayout.WEST);
        loginPanel.add(passwordField, BorderLayout.CENTER);

        // login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());
        buttonPanel.add(loginButton);

        // register
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> new RegisterScreen());
        buttonPanel.add(registerButton);

        // panels
        add(loginPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // frame
        setSize(300,150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void login() {
        if (userList.contains(usernameField.getText())) {
            if (userList.get(usernameField.getText()).login(passwordField.getPassword())) {
                JOptionPane.showMessageDialog(this,"환영합니다.");
            } else {
                JOptionPane.showMessageDialog(this, "암호가 틀렸습니다. 다시 입력해 보세요.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "존재하지 않는 아이디입니다.\n" +
                    "아이디가 올바른지 확인하거나 회원가입을 하세요.");
        }
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "message");
        new LoginScreen();
    }
}
