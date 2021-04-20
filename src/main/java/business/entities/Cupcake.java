package business.entities;

public class Cupcake {

    private int toppingID;
    private int bottomID;
    private int amount;
    private int price;

    public Cupcake(int toppingID, int bottomID, int amount, int price) {
        this.toppingID = toppingID;
        this.bottomID = bottomID;
        this.amount = amount;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getToppingID() {
        return toppingID;
    }

    public int getBottomID() {
        return bottomID;
    }

    public int getAmount() {
        return amount;
    }
}
