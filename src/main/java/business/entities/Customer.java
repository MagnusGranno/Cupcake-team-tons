package business.entities;

public class Customer
{
    private int id;
    private String email;
    private int balance;

    public Customer(int id, String email, int balance)
    {
        this.id = id;
        this.email = email;
        this.balance = balance;
    }

    public int getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public int getBalance()
    {
        return balance;
    }
}
