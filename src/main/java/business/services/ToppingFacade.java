package business.services;

import business.entities.Topping;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.ToppingMapper;

import java.util.List;

public class ToppingFacade
{
    private ToppingMapper toppingMapper;

    public ToppingFacade(Database database)
    {
        this.toppingMapper = new ToppingMapper(database);
    }

    public List<Topping> getAllToppings() throws UserException
    {
        return toppingMapper.getAllToppings();
    }
}
