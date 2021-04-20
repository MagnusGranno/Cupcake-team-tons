package business.persistence;

import business.entities.Customer;
import business.entities.Topping;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                while(rs.next())
                {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    int balance = rs.getInt("balance");

                    customerList.add(new Customer(id, email, balance));
                }
                return customerList;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch(SQLException ex)
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
            try(PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    oldBalance = rs.getInt("balance");
                }
                return oldBalance;
            }catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public int updateCustomerBalance(int amount, int id) throws UserException
    {
        int oldBalance = getOldBalance(id);

        int rowsAffected = 0;
        try(Connection connection = database.connect())
        {
            String sql = "UPDATE Cupcake.users SET balance=? WHERE id=?;";
            try(PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1,amount+oldBalance);
                ps.setInt(2,id);

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


}
