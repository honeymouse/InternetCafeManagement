package client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.*;

/**
 * 음식 주문 화면
 * @author 이름 작성하세요
 *
 */
public class FoodScreen extends JFrame {

//	private TreeMap<Food, Integer> setNoodle() {
//		TreeMap<Food, Integer> map = new TreeMap<Food, Integer>();
//		map.put(new Food("신라면", 2000), 0);
//		map.put(new Food("진라면", 2000), 0);
//		map.put(new Food("삼양라면", 2000), 0);
//		map.put(new Food("비빔면", 2000), 0);
//		map.put(new Food("불닭", 2000), 0);
//		map.put(new Food("짜파게티", 2000), 0);
//		map.put(new Food("계란 토핑", 500), 0);
//		map.put(new Food("치즈", 500), 0);
//		map.put(new Food("공기밥", 1000), 0);
//		return map;
//	}

    private Food[] ramenList = {
            new Food("신라면", 2000),
            new Food("진라면", 2000),
            new Food("삼양라면", 2000),
            new Food("비빔면", 2000),
            new Food("참깨라면", 2000),
            new Food("불닭", 2000),
            new Food("짜파게티", 2000),
            new Food("치즈", 500),
            new Food("공기밥", 1000)
    };

    private Food[] cafeList = {
            new Food("아메리카노", 1000),
            new Food("카페라떼", 2000),
            new Food("카푸치노", 2000),
            new Food("카페모카", 3000),
            new Food("바닐라라떼", 3000),
            new Food("마끼아또", 2500),
            new Food("에스프레소", 1000),
            new Food("오렌지쥬스", 3000),
            new Food("녹차", 2000)
    };


    private JPanel navBar;
    private JButton[] navButtons;
    private JPanel middlePanel;
    private JPanel userPanel;
    private JPanel orderPanel;
    private JPanel itemOrderPanel;
    private JPanel totalPricePanel;
    private int totalPrice;
    ArrayList<Food> orderList;

    public FoodScreen() {
        super("음식주문");
        initNavBar();
        initMiddlePanel();
        initUserPanel();
        totalPrice = 0;
        orderList = new ArrayList<Food>();
        setLayout(new BorderLayout());
        add(navBar, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(userPanel, BorderLayout.EAST);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton deleteItem;
    private JButton totalPriceButton;
    public void initUserPanel( ) {
        userPanel = new JPanel();
        userPanel.setSize(300, 600);
        userPanel.setBackground(Color.WHITE);
        userPanel.setLayout(new BorderLayout());



        totalPricePanel = new JPanel();


        orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setSize(100, 300);
        deleteItem = new JButton("삭제");

        totalPriceButton = new JButton(totalPrice + "원");
        userPanel.add(totalPriceButton, BorderLayout.SOUTH);


        JButton orderButton = new JButton("주문 완료");
        orderButton.addActionListener(e -> sendOrder());


        orderPanel.add(deleteItem, BorderLayout.NORTH);

        itemOrderPanel = new JPanel();
        itemOrderPanel.setLayout(new BoxLayout(itemOrderPanel, BoxLayout.Y_AXIS));
        itemOrderPanel.setBackground(Color.WHITE);
        orderPanel.add(itemOrderPanel, BorderLayout.CENTER);


        orderPanel.add(orderButton, BorderLayout.SOUTH);
//		orderPanel.add(scrollPane);
        deleteItem.addActionListener(e -> {

            for( Component comp : itemOrderPanel.getComponents() ) {
                if(comp instanceof JCheckBox && ((JCheckBox)comp).isSelected()) {
                    String itemToDelete = (comp.getName());
                    for (int i=0; i<ramenList.length; i++) {
                        if (ramenList[i].getName().equals(itemToDelete))
                            totalPrice -= ramenList[i].getPrice();
                    }
                    System.out.println(itemToDelete);

                    itemOrderPanel.remove(comp);
                }

            }
            refreshTotalPriceButton();
            revalidate();
            repaint();
        });



        userPanel.add(orderPanel, BorderLayout.CENTER);
    }

    public void refreshTotalPriceButton() {
        totalPriceButton.setText(totalPrice+"원");
    }

    public void sendOrder() {
        FoodOrder order;
        JOptionPane.showMessageDialog(null, "포함된 기능이 아닙니다.");
    }

    public void initNavBar() {
        navBar = new JPanel();
        navBar.setLayout(new GridLayout(5, 1));
        navBar.setSize(50,600);
        navBar.setBackground(Color.RED);

        navButtons = new JButton[5];
        navButtons[0] = new JButton("라면");
        navButtons[1] = new JButton("카페");
        navButtons[2] = new JButton("밥");
        navButtons[3] = new JButton("음료");
        navButtons[4] = new JButton("시간");

        for (JButton button : navButtons) {
            button.setForeground(new Color(228, 227, 242));
            button.setBackground(new Color(12, 7, 76));
            if (button.isSelected()) {
                button.setForeground(new Color(0, 227, 242));
            }
            button.addActionListener(e -> {
                middlePanel.removeAll();

                switch (button.getText()) {
                    case "라면":
                        switchToRamenPanel();
                        break;
                    case "카페":
                        switchToCafePanel();
                        break;
                    case "밥":
                        switchToRicePanel();
                        break;
                    case "음료":
                        switchToDrinkPanel();
                        break;
                    case "시간":
                        switchToTimePanel();
                        break;
                }
            });


            navBar.add(button);
        }
    }


    public void switchToRamenPanel() {
        middlePanel.revalidate();
        middlePanel.repaint();
        JPanel ramen = new JPanel();
        ramen.setLayout(new GridLayout(3, 3));
        for (int i=0; i<9; i++) {
            Food currentItem = ramenList[i];
            JButton item = new JButton(ramenList[i].getName());
            item.addActionListener(e -> {
                JCheckBox checkBox = new JCheckBox(currentItem.getName());
                checkBox.setName(currentItem.getName());
                checkBox.setOpaque(false);
                itemOrderPanel.add(checkBox, BorderLayout.CENTER);
                orderList.add(currentItem);
                totalPrice += currentItem.getPrice();
                refreshTotalPriceButton();
                revalidate();
                repaint();
            });
            ramen.add(item);
        }
        ramen.setBackground(Color.BLUE);
        middlePanel.add(ramen);

    }

    public void switchToCafePanel() {
        middlePanel.revalidate();
        middlePanel.repaint();
        JPanel cafe = new JPanel();
        cafe.setLayout(new GridLayout(3, 3));
        for (int i=0; i<9; i++) {
            Food currentItem = cafeList[i];
            JButton item = new JButton(cafeList[i].getName());
            item.addActionListener(e -> {
                JCheckBox checkBox = new JCheckBox(item.getText());
                checkBox.setOpaque(false);
                itemOrderPanel.add(checkBox, BorderLayout.CENTER);
                revalidate();
                repaint();
            });
            cafe.add(item);
        }
        cafe.setBackground(Color.CYAN);
        middlePanel.add(cafe);
    }

    public void switchToRicePanel() {
        middlePanel.revalidate();
        middlePanel.repaint();
        JPanel rice = new JPanel();
        rice.setBackground(Color.RED);
        middlePanel.add(rice);
    }

    public void switchToDrinkPanel() {
        middlePanel.revalidate();
        middlePanel.repaint();
        JPanel drink = new JPanel();
        drink.setBackground(Color.PINK);
        middlePanel.add(drink);
    }

    public void switchToTimePanel() {
        middlePanel.revalidate();
        middlePanel.repaint();
        JPanel time = new JPanel();
        time.setBackground(Color.GREEN);
        middlePanel.add(time);
    }

    public void initMiddlePanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1,1));
        switchToRamenPanel();
    }

    public static void main(String[] args) {
        new FoodScreen();

    }
}
