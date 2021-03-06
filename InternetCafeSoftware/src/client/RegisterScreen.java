package client;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.LoggerProvider;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class RegisterScreen {
    private static JFrame frame;

    public RegisterScreen() {
        LoggerProvider.setLevel(Level.OFF);

        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        frame = new JFrame();
        frame.getContentPane().add(view, BorderLayout.CENTER);
        frame.setSize(250, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    Browser browser = event.getBrowser();
                    JSValue value = browser.executeJavaScriptAndReturnValue("window");
                    value.asObject().setProperty("Account", new RegisterScreen.Account());
                }
            }
        });

        browser.loadURL("file:///Users/almond/Documents/Programming/InternetCafeSoftware/src/client/form.html");
    }



    public static class Account {
        public void save(String name, String user, String pass, String gender) {
            System.out.println("name = " + name);
            new User(user, pass.toCharArray(), name, Integer.parseInt(gender));
            JOptionPane.showMessageDialog(null, "환영합니다, " +name+"님.");
            frame.dispose();
        }
        public void nothingreally() {
            System.out.println("done");
        }

    }

    public static void main(String[] args) {
        new RegisterScreen();
    }

}