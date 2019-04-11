package client;

/**
 * 음식
 * @author pc
 *
 */
public class Food {

    private String name;
    private int price;

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String toString() {
        return name + " " + price + "원";
    }
}