package business.entities;

public class CupcakeFromDB {

    private int toppingID;
    private int bottomID;
    private int amount;
    private int price;

    public CupcakeFromDB(int toppingID, int bottomID, int price, int amount) {
        this.toppingID = toppingID;
        this.bottomID = bottomID;
        this.amount = amount;
        this.price = price;
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

    public int getPrice() {
        return price;
    }
}
