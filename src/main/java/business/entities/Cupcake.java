package business.entities;

public class Cupcake {

    private Topping topping;
    private Bottom bottom;
    private int amount;
    private int price;

    public Cupcake(Topping topping, Bottom bottom, int amount, int price) {
        this.topping = topping;
        this.bottom = bottom;
        this.amount = amount;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Topping getTopping() {
        return topping;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public int getAmount() {
        return amount;
    }





}
