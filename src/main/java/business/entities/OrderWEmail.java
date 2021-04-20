package business.entities;

import java.sql.Timestamp;

public class OrderWEmail
{
    private int order_id;
    private int user_id;
    private String email;
    private int total_price;
    private Timestamp timestamp;

    public String getEmail()
    {
        return email;
    }

    public OrderWEmail(int order_id, int user_id, String email, int total_price, Timestamp timestamp)
    {
        this.order_id = order_id;
        this.user_id = user_id;
        this.email = email;
        this.total_price = total_price;
        this.timestamp = timestamp;
    }

    public int getOrder_id()
    {
        return order_id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public int getTotal_price()
    {
        return total_price;
    }

    public Timestamp getTimestamp()
    {
        return timestamp;
    }
}
