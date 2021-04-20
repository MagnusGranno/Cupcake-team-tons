package business.services;

import business.entities.Cupcake;
import business.exceptions.UserException;
import business.persistence.CartMapper;
import business.persistence.Database;

import java.util.List;

public class CartFacade{

    private Database database;
    private CartMapper cartMapper;


    public CartFacade(Database database) {
        this.database = database;
        this.cartMapper = new CartMapper(database);
    }


   /* public List<CupcakeFromDB> getCupcakesFromDB(int orderID) throws UserException {

        List<CupcakeFromDB> cupcakeFromDBList = cartMapper.getCupcakesFromDB(orderID);

        return cupcakeFromDBList;

    }*/



}
