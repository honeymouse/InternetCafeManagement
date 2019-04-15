package client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ClientScreen extends JFrame {

    private User user;
    private JPanel navPanel;

    public ClientScreen(User user) {
        super("Client");
        this.user = user;

        setSize(400, 120);
        setResizable(false);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - getWidth();
        int y = 0;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel intro = new JLabel();
        intro.setText("안녕하세요, " + user.getUsername() + "님.");
        add(intro, BorderLayout.NORTH);
        navPanel = new JPanel();
        add(navPanel, BorderLayout.SOUTH);

        initTimerPanel();
        initFoodOrderButton();
        initChatButton();
        initQuitButton();

        setVisible(true);
    }

    private void initFoodOrderButton() {
        JButton orderButton = new JButton();
        orderButton.setName("먹거리");
        orderButton.setText("먹거리");
        orderButton.addActionListener(e -> new FoodScreen(user));
        navPanel.add(orderButton, BorderLayout.CENTER);
    }

    private void initQuitButton() {
        JButton quitButton = new JButton();
        quitButton.setText("사용종료");
        quitButton.addActionListener(e -> {
            UserList.UPDATEUSER(user);
            System.exit(0);
        });
        navPanel.add(quitButton, BorderLayout.CENTER);
    }

    private void initChatButton() {
        JButton chatButton = new JButton();
        chatButton.setText("채팅");
        chatButton.addActionListener(e -> new ChatScreen(user));
        navPanel.add(chatButton, BorderLayout.CENTER);

    }

    private JLabel timeLeftLabel;
    private Timer timer;
    private void initTimerPanel() {
        JPanel timerPanel = new JPanel();
        timer = new Timer();

        timeLeftLabel = new JLabel();
        timeLeftLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        timeLeftLabel.setText(timer.getTimeLeft());

        Thread th = new Thread(timer);
        th.setDaemon(true);
        th.start();

        timerPanel.add(timeLeftLabel);
        add(timerPanel, BorderLayout.CENTER);
    }

    synchronized private void updateTime() {
        timeLeftLabel.setText(timer.getTimeLeft());
    }

    private class Timer implements Runnable {
        private int timeElapsed=0, timeLeft;

        @Override
        public void run() {

            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeElapsed++;
                timeLeft = user.getTimeLeft();
                if (timeElapsed % 60 == 0) {
                    user.removeMinute();
                }
                updateTime();
            }
        }
        public String getTimeLeft() {
            int hours = timeLeft / 60;
            int minutes = timeLeft % 60;
            return String.format("남은 시간 %d시간 %02d분", hours, minutes);
        }
    }




}
