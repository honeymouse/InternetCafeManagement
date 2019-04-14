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

//   private TreeMap<Food, Integer> setNoodle() {
//      TreeMap<Food, Integer> map = new TreeMap<Food, Integer>();
//      map.put(new Food("신라면", 2000), 0);
//      map.put(new Food("진라면", 2000), 0);
//      map.put(new Food("삼양라면", 2000), 0);
//      map.put(new Food("비빔면", 2000), 0);
//      map.put(new Food("불닭", 2000), 0);
//      map.put(new Food("짜파게티", 2000), 0);
//      map.put(new Food("계란 토핑", 500), 0);
//      map.put(new Food("치즈", 500), 0);
//      map.put(new Food("공기밥", 1000), 0);
//      return map;
//   }

    private Food[] ramenList = {
            new Food("신라면 : 2000원", 2000),
            new Food("진라면 : 2000원", 2000),
            new Food("삼양라면 : 2000원", 2000),
            new Food("비빔면 : 2000원", 2000),
            new Food("참깨라면 : 2000원", 2000),
            new Food("불닭 : 2000원", 2000),
            new Food("짜파게티 : 2000원", 2000),
            new Food("치즈 : 500원", 500),
            new Food("공기밥 : 1000원", 1000)
    };

    private Food[] cafeList = {
            new Food("아메리카노 : 1000원", 1000),
            new Food("카페라떼 : 2000원", 2000),
            new Food("카푸치노 : 2000원", 2000),
            new Food("카페모카 : 3000원", 3000),
            new Food("바닐라라떼 : 3000원", 3000),
            new Food("마끼아또 : 2500원", 2500),
            new Food("에스프레소 : 1000원", 1000),
            new Food("오렌지쥬스 : 3000원", 3000),
            new Food("녹차 : 2000원", 2000)
    };
    private Food[] riceList = {
            new Food("김치 볶음밥 : 5000원",5000),
            new Food("오징어 덮밥 : 5000원",5000),
            new Food("제육 덮밥 : 5000원",5000),
            new Food("불고기 덮밥 : 5000원",5000),
            new Food("새우 볶음밥 : 5000원",5000),
            new Food("짜장밥 : 5000원",5000),
            new Food("계란 덮밥 : 5000원",5000),
            new Food("돈까스 덮밥 : 5000원",5000),
            new Food("야채 볶음밥 : 5000원",5000),

    };
    private Food[] drinkList = {
            new Food("환타 : 1000원",1000),
            new Food("콜라 : 1000원",1000),
            new Food("스프라이트 : 1000원",1000),
            new Food("레몬에이드 : 1000원",1000),
            new Food("포도봉봉 : 1000원",1000),
            new Food("Hot6 : 1000원",1000),
            new Food("밀키스 : 1000원",1000),
            new Food("식혜 : 1000원",1000),
            new Food("갈아만든배 : 1000원",1000),
    };
    private Food[] timeList = {
            new Food("1시간 추가",1000),
            new Food("2시간 추가",2000),
            new Food("3시간 추가",3000),
            new Food("4시간 추가",4000),
            new Food("5시간 추가",5000),
            new Food("6시간 추가",6000),
            new Food("7시간 추가",7000),
            new Food("8시간 추가",8000),
            new Food("10시간 추가",10000),
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
//      orderPanel.add(scrollPane);
        deleteItem.addActionListener(e -> {

            for( Component comp : itemOrderPanel.getComponents() ) {
                if(comp instanceof JCheckBox && ((JCheckBox)comp).isSelected()) {
                    String itemToDelete = (comp.getName());
                    for (int i=0; i<ramenList.length; i++) {
                        if (ramenList[i].getName().equals(itemToDelete))
                            totalPrice -= ramenList[i].getPrice();
                    }
                    for (int i=0; i<cafeList.length; i++) {
                        if (cafeList[i].getName().equals(itemToDelete))
                            totalPrice -= cafeList[i].getPrice();
                    }
                    for (int i=0; i<riceList.length; i++) {
                        if (riceList[i].getName().equals(itemToDelete))
                            totalPrice -= riceList[i].getPrice();
                    }
                    for (int i=0; i<drinkList.length; i++) {
                        if (drinkList[i].getName().equals(itemToDelete))
                            totalPrice -= drinkList[i].getPrice();
                    }
                    for (int i=0; i<timeList.length; i++) {
                        if (timeList[i].getName().equals(itemToDelete))
                            totalPrice -= timeList[i].getPrice();
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
        FoodOrder order; // 서버로 보내기
        JOptionPane.showMessageDialog(null, "(WIP) 주문이 정상적으로 접수되었습니다.");
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
                checkBox.setName(currentItem.getName());
                checkBox.setOpaque(false);
                itemOrderPanel.add(checkBox, BorderLayout.CENTER);
                orderList.add(currentItem);
                totalPrice += currentItem.getPrice();
                refreshTotalPriceButton();
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
        rice.setLayout(new GridLayout(3,3));
        for(int i =0; i<9; i++) {
            Food currentItem = riceList[i];
            JButton item = new JButton(riceList[i].getName());
            item.addActionListener(e -> {
                JCheckBox checkBox = new JCheckBox(item.getText());
                checkBox.setName(currentItem.getName());
                checkBox.setOpaque(false);
                itemOrderPanel.add(checkBox, BorderLayout.CENTER);
                orderList.add(currentItem);
                totalPrice += currentItem.getPrice();
                refreshTotalPriceButton();
                revalidate();
                repaint();
            });
            rice.add(item);
        }
        rice.setBackground(Color.RED);
        middlePanel.add(rice);
    }

    public void switchToDrinkPanel() {
        middlePanel.revalidate();
        middlePanel.repaint();
        JPanel drink = new JPanel();
        drink.setLayout(new GridLayout(3,3));
        for(int i =0; i<9; i++) {
            Food currentItem = drinkList[i];
            JButton item = new JButton(drinkList[i].getName());
            item.addActionListener(e -> {
                JCheckBox checkBox = new JCheckBox(item.getText());
                checkBox.setName(currentItem.getName());
                checkBox.setOpaque(false);
                itemOrderPanel.add(checkBox, BorderLayout.CENTER);
                orderList.add(currentItem);
                totalPrice += currentItem.getPrice();
                refreshTotalPriceButton();
                revalidate();
                repaint();
            });
            drink.add(item);
        }
        drink.setBackground(Color.PINK);
        middlePanel.add(drink);
    }

    public void switchToTimePanel() {
        middlePanel.revalidate();
        middlePanel.repaint();
        JPanel time = new JPanel();
        time.setLayout(new GridLayout(3,3));
        for(int i = 0; i <9; i++) {
            Food currentItem = timeList[i];
            JButton item = new JButton(timeList[i].getName());
            item.addActionListener(e -> {
                JCheckBox checkBox = new JCheckBox(item.getText());
                checkBox.setName(currentItem.getName());
                checkBox.setOpaque(false);
                itemOrderPanel.add(checkBox, BorderLayout.CENTER);
                orderList.add(currentItem);
                totalPrice += currentItem.getPrice();
                refreshTotalPriceButton();
                revalidate();
                repaint();
            });
            time.add(item);
        }
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