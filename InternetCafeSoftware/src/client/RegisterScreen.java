package client;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The example demonstrates how to integrate UI built with HTML+CSS+JavaScript
 * into Java desktop application.
 */
public class RegisterScreen {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("HTMLUISample");
        final JButton newAccountButton = new JButton("New Account...");
        newAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = createAccount(frame);
                // Displays created account's details
                JOptionPane.showMessageDialog(frame, "Created Account: " + account);
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.add(newAccountButton);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(contentPane, BorderLayout.CENTER);
        frame.setSize(300, 75);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static Account createAccount(JFrame parent) {
        final AtomicReference<Account> result = new AtomicReference<Account>();
        final JDialog dialog = new JDialog(parent, "New Account", true);

        // Create Browser instance.
        final Browser browser = new Browser();
        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    JSValue value = browser.executeJavaScriptAndReturnValue("window");
                    value.asObject().setProperty("AccountService",
                            new AccountService(dialog, result));
                }
            }
        });
        // Load HTML with dialog's HTML+CSS+JavaScript UI.
        browser.loadURL("file:///Users/almond/Documents/Programming/InternetCafeSoftware/src/dialog.html");

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Dispose Browser instance because we don't need it anymore.
                browser.dispose();
                // Close New Account dialog.
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Embed Browser Swing component into the dialog.
        dialog.add(new BrowserView(browser), BorderLayout.CENTER);
        dialog.setSize(400, 500);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);

        return result.get();
    }

    public static class AccountService {

        private final JDialog dialog;
        private final AtomicReference<Account> result;

        public AccountService(JDialog dialog, AtomicReference<Account> result) {
            this.dialog = dialog;
            this.result = result;
        }

        public void createAccount(String firstName, String lastName, String phone, String email) {
            result.set(new Account(firstName, lastName, phone, email));
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    dialog.setVisible(false);
                }
            });
        }
    }

    public static class Account {
        public final String firstName;
        public final String lastName;
        public final String phone;
        public final String email;

        public Account(String firstName, String lastName, String phone, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}

///*
//package client;
//
//import com.teamdev.jxbrowser.chromium.Browser;
//import com.teamdev.jxbrowser.chromium.JSValue;
//import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
//import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
//import com.teamdev.jxbrowser.chromium.swing.BrowserView;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.util.concurrent.atomic.AtomicReference;
//
//public class RegisterScreen {
//    private UserList userList;
//    public RegisterScreen(JFrame frame) {
//        userList = new UserList();
//        User user = createAccount(frame);
//        userList.add(user.getUsername(), user);
//        JOptionPane.showMessageDialog(frame, "정상적으로 처리되었습니다.");
//    }
//
//    private static User createAccount(JFrame parent) {
//        final AtomicReference<User> result = new AtomicReference<User>();
//        final JDialog dialog = new JDialog(parent, "New Account", true);
//
//        // Create Browser instance.
//        final Browser browser = new Browser();
//        browser.addLoadListener(new LoadAdapter() {
//            @Override
//            public void onFinishLoadingFrame(FinishLoadingEvent event) {
//                if (event.isMainFrame()) {
//                    JSValue value = browser.executeJavaScriptAndReturnValue("window");
//                    value.asObject().setProperty("AccountService",
//                            new AccountService(dialog, result));
//                }
//            }
//        });
//        // Load HTML with dialog's HTML+CSS+JavaScript UI.
//        browser.loadURL("file:///Users/almond/Documents/Programming/InternetCafeSoftware/src/registrationform.html");
//
//        dialog.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                // Dispose Browser instance because we don't need it anymore.
//                browser.dispose();
//                // Close New Account dialog.
//                dialog.setVisible(false);
//                dialog.dispose();
//            }
//        });
//        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//        // Embed Browser Swing component into the dialog.
//        dialog.add(new BrowserView(browser), BorderLayout.CENTER);
//        dialog.setSize(400, 500);
//        dialog.setResizable(false);
//        dialog.setLocationRelativeTo(parent);
//        dialog.setVisible(true);
//
//        return result.get();
//    }
//
//
//    public static class AccountService {
//
//        private final JDialog dialog;
//        private final AtomicReference<User> result;
//
//        public AccountService(JDialog dialog, AtomicReference<User> result) {
//            this.dialog = dialog;
//            this.result = result;
//        }
//
//        public void createAccount(String username, char[] password, String fullname, int birthdate, int gender, int phone, String email) {
//            result.set(new User(username, password, fullname, birthdate, gender, phone, email));
//            SwingUtilities.invokeLater(() -> dialog.setVisible(false));
//        }
//    }
//}
//*/
