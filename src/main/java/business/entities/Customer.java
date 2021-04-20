package business.entities;

import java.sql.Timestamp;

public class Customer
{
    private int id;
    private String email;
    private int balance;
    private Timestamp timestamp;

    public Customer(int id, String email, int balance, Timestamp timestamp)
    {
        this.id = id;
        this.email = email;
        this.balance = balance;
        this.timestamp = timestamp;
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

    public Timestamp getTimestamp()
    {
        return timestamp;
    }
}
