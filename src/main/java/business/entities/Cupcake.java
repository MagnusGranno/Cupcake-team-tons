package business.entities;

public class Cupcake {

    private int toppingID;
    private int bottomID;
    private int amount;

    public Cupcake(int toppingID, int bottomID, int amount) {
        this.toppingID = toppingID;
        this.bottomID = bottomID;
        this.amount = amount;
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
