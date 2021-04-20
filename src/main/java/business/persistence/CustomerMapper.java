package business.persistence;

import business.entities.Customer;
import business.entities.Order;
import business.entities.Topping;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper
{
    private Database database;

    public CustomerMapper(Database database)
    {
        this.database = database;
    }

    public List<Customer> getAllcustomers() throws UserException
    {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM Cupcake.users WHERE role = 'customer';";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    int balance = rs.getInt("balance");
                    Timestamp timestamp = rs.getTimestamp("create_time");

                    customerList.add(new Customer(id, email, balance, timestamp));
                }
                return customerList;
            } catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public int getOldBalance(int id) throws UserException
    {
        int oldBalance = 0;
        try (Connection connection = database.connect())
        {
            String sql = "SELECT balance FROM Cupcake.users WHERE id = ?;";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    oldBalance = rs.getInt("balance");
                }
                return oldBalance;
            } catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public int updateCustomerBalance(int amount, int id) throws UserException
    {
        int oldBalance = getOldBalance(id);

        int rowsAffected = 0;
        try (Connection connection = database.connect())
        {
            String sql = "UPDATE Cupcake.users SET balance=? WHERE id=?;";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, amount + oldBalance);
                ps.setInt(2, id);

                rowsAffected = ps.executeUpdate();

                return rowsAffected;
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return rowsAffected;
    }


    public String getCustomerEmailByID(int id) throws UserException
    {
        String email = "";
        try (Connection connection = database.connect())
        {
            String sql = "SELECT email from Cupcake.users WHERE id = ?;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    email = rs.getString("email");
                    return email;
                }

            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return email;
    }

    public List<Order> getCustomerOrderByCustomerId(int id) throws UserException
    {
        List<Order> customerOrderList = new ArrayList<>();

        try(Connection connection = database.connect())
        {
            String sql = "SELECT * FROM Cupcake.order WHERE user_id = ?;";

            try(PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    int order_id = rs.getInt("order_id");
                    int user_id = rs.getInt("user_id");
                    int total_price = rs.getInt("total_price");
                    Timestamp timestamp = rs.getTimestamp("timestamp");

                    customerOrderList.add(new Order(order_id, user_id, total_price, timestamp));
                }
                return customerOrderList;
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return customerOrderList;
    }

}
