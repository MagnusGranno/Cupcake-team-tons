package business.persistence;

import business.entities.Cupcake;
import business.entities.Topping;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderMapper {

    private Database database;
    private ToppingMapper toppingMapper;

    public OrderMapper(Database database) {
        this.database = database;
    }


    public void insertCupcakesIntoDB(Cupcake cupcake) throws UserException {

        /*int toppingPrice;
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
        }*/


        int price = 0;

        try (Connection connection = database.connect()){

            String sql = "SELECT SUM(bottom.price) + SUM(topping.price) AS 'total_price' FROM bottom INNER JOIN topping on topping.price = bottom.price WHERE bottom_id = ? AND topping_id = ?;";


            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,cupcake.getBottomID());
                ps.setInt(2,cupcake.getToppingID());

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
                ps.setInt(2,cupcake.getToppingID());
                ps.setInt(3,cupcake.getBottomID());
                ps.setInt(4,cupcake.getAmount());
                ps.setInt(5,price);

                ps.executeUpdate();


            } catch (SQLException e){

                throw new UserException(e.getMessage());
            }
        }catch (SQLException e){
            throw new UserException(e.getMessage());
        }
    }
}