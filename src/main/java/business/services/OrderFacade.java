package business.services;

import business.entities.Cupcake;
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


}
