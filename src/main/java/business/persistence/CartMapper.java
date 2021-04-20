package business.persistence;

import business.entities.Cupcake;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartMapper {

    private Database database;

    public CartMapper(Database database) {
        this.database = database;
    }


    /*public List<CupcakeFromDB> getCupcakesFromDB(int orderID) throws UserException {

        List<CupcakeFromDB> cupcakeList = null;

        try(Connection connection = database.connect()){

            String sql = "SELECT * FROM Cupcake.cupcake WHERE order_id = ?;";


            try (PreparedStatement ps = connection.prepareStatement(sql)){

                ps.setInt(1,orderID);

                ResultSet rs = ps.executeQuery();

                while(rs.next()){

                    cupcakeList = new ArrayList<>();
                    cupcakeList.add(new CupcakeFromDB(
                            rs.getInt("topping_id"),
                            rs.getInt("bottom_id"),
                            rs.getInt("price"),
                            rs.getInt("amount")));
                }



            }
        } catch (SQLException e){
            throw new UserException(e.getMessage());
        }
        return cupcakeList;
    }*/

}
