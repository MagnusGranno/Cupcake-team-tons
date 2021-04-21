package business.persistence;

import business.entities.Cupcake;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.List;

public class AddItemsMapper {

    Database database;

    public AddItemsMapper(Database database) {
        this.database = database;
    }

    public void addItemsToOrder(List<Cupcake> cupcakeList, User user) throws UserException {

        CustomerMapper customerMapper = new CustomerMapper(database);

        int total = 0;
        int id = 0;

        try (Connection connection = database.connect()) {

            String sql = "INSERT INTO Cupcake.order(user_id,total_price) VALUES (?,?);";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                for (Cupcake item : cupcakeList) {
                    total = item.getPrice();
                }

                ps.setInt(1, user.getId());
                ps.setInt(2, total);

                ps.executeUpdate();

                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                id = ids.getInt(1);

            } catch (SQLException e) {
                throw new UserException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }

        for (Cupcake item : cupcakeList) {
            try (Connection connection = database.connect()) {

                String sql = "INSERT INTO Cupcake.cupcake(order_id,topping_id,bottom_id,price,amount) VALUES (?,?,?,?,?);";

                try (PreparedStatement ps = connection.prepareStatement(sql)) {

                    ps.setInt(1, id);
                    ps.setInt(2,item.getTopping().getId());
                    ps.setInt(3,item.getBottom().getId());
                    ps.setInt(4,item.getPrice());
                    ps.setInt(5,item.getAmount());

                    ps.executeUpdate();

                } catch (SQLException e) {
                    throw new UserException(e.getMessage());
                }
            } catch (SQLException e) {
                throw new UserException(e.getMessage());
            }
        }

        int totalMinus = Math.negateExact(total);

        customerMapper.updateCustomerBalance(totalMinus,user.getId());

    }
}
