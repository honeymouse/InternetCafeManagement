package client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FoodOrder {
    private ArrayList<Food> orderList;
    private String name;
    private String seat;
    private String orderTime;
    private int totalCost;

    public FoodOrder(String name, String seat) {
        this.name = name;
        this.seat = seat;
        totalCost = 0;
    }

    public void add(Food food) {
        orderList.add(food);
        totalCost += food.getPrice();
    }

    public void addList(ArrayList<Food> orderList, int totalCost, User user) {
        this.orderList = orderList;
        this.totalCost = totalCost;
        name = user.getName();
    }

    public ArrayList<Food> getOrderList() { return orderList; }

    public String getName() { return name; }

    public void remove(int index) {
        totalCost -= orderList.remove(index).getPrice();
    }

    public void confirm() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        orderTime = dtf.format(now);
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String getOrderTime() {
        return orderTime;
    }

}
