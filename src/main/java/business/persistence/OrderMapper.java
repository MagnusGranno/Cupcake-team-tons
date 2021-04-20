package business.persistence;

import business.entities.*;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderMapper {

    CustomerMapper customerMapper;
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }


    /*public void insertCupcakesIntoDB(Cupcake cupcake) throws UserException {

        int toppingPrice;
        int bottomPrice;


        try (Connection connection = database.connect()){

            String sql = "SELECT price from bottom where bottom_id = ?;";


            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,cupcake.getBottomID());

                ResultSet rs = ps.executeQuery();

                bottomPrice = rs.getInt("price");

            } catch (SQLException e){

                throw new UserException(e.getMessage());
            }
        }catch (SQLException e){
            throw new UserException(e.getMessage());
        }

        try (Connection connection = database.connect()){

            String sql = "SELECT price from topping where topping_id = ?;";


            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,cupcake.getToppingID());

                ResultSet rs = ps.executeQuery();

                toppingPrice = rs.getInt("price");

            } catch (SQLException e){

                throw new UserException(e.getMessage());
            }
        }catch (SQLException e){
            throw new UserException(e.getMessage());
        }


        int price = 0;

        try (Connection connection = database.connect()){

            String sql = "SELECT (SELECT SUM(topping.price) FROM topping WHERE topping_id = ?) + SUM(bottom.price) AS 'total price' FROM bottom WHERE bottom_id = ?;";


            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,cupcake.getTopping());
                ps.setInt(2,cupcake.getBottom());

                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    price = rs.getInt(1);
                }
            } catch (SQLException e){

                throw new UserException(e.getMessage());
            }
        }catch (SQLException e){
            throw new UserException(e.getMessage());
        }


        try (Connection connection = database.connect()){

            String sql = "INSERT INTO Cupcake.cupcake (order_id,topping_id,bottom_id,price,amount) VALUES(?,?,?,?,?);";

            try (PreparedStatement ps = connection.prepareStatement(sql)){

                ps.setInt(1,1);
                ps.setInt(2,cupcake.getTopping());
                ps.setInt(3,cupcake.getBottom());
                ps.setInt(4,price*cupcake.getAmount());
                ps.setInt(5,cupcake.getAmount());


                ps.executeUpdate();


            } catch (SQLException e){

                throw new UserException(e.getMessage());
            }
        }catch (SQLException e){
            throw new UserException(e.getMessage());
        }

    }*/


    public List<Order> getAllOrders() throws UserException
    {
//        List<OrderWEmail> orderWEmailList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM Cupcake.order;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    int order_id = rs.getInt("order_id");
                    int user_id = rs.getInt("user_id");
                    int total_price = rs.getInt("total_price");
                    Timestamp timestamp = rs.getTimestamp("timestamp");


                    orderList.add(new Order(order_id, user_id, total_price, timestamp));
//                    String email = customerMapper.getCustomerEmailByID(rs.getInt("user_id"));
//                    orderWEmailList.add(new OrderWEmail(order_id,user_id,email, total_price, timestamp));

                }
                return orderList;
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
    public List<OrderWEmail> getAllOrdersWEmail() throws UserException
    {
        List<OrderWEmail> orderWEmailList = new ArrayList<>();

        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM Cupcake.order;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    int order_id = rs.getInt("order_id");
                    int user_id = rs.getInt("user_id");
                    String email = getCustomerEmailByID(user_id);
                    int total_price = rs.getInt("total_price");
                    Timestamp timestamp = rs.getTimestamp("timestamp");

                    orderWEmailList.add(new OrderWEmail(order_id, user_id, email, total_price, timestamp));

                }
                return orderWEmailList;
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
    public String getCustomerEmailByID (int id) throws UserException
    {
        String email = "";
        try(Connection connection = database.connect())
        {
            String sql = "SELECT email from Cupcake.users WHERE id = ?;";

            try(PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
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
}