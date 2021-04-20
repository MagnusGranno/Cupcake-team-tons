package business.services;

import business.entities.Cupcake;
import business.entities.Order;
import business.entities.OrderWEmail;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.util.List;

public class OrderFacade {

    private OrderMapper orderMapper;

    public OrderFacade(Database database) {
        this.orderMapper = new OrderMapper(database);
    }


    /*public void insertCupcakesIntoDB(Cupcake cupcake) throws UserException {

        orderMapper.insertCupcakesIntoDB(cupcake);

    }*/

    public List<Order> getAllOrders() throws UserException {
        return orderMapper.getAllOrders();
    }

    public List<OrderWEmail> getAllOrdersWEmail() throws UserException
    {
        return orderMapper.getAllOrdersWEmail();
    }

    public int deleteOrderById (int id) throws UserException
    {
        return orderMapper.deleteOrderById(id);
    }


}
