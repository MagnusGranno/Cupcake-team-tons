package business.services;

import business.entities.Customer;
import business.exceptions.UserException;
import business.persistence.CustomerMapper;
import business.persistence.Database;

import java.util.List;

public class CustomerFacade
{
    CustomerMapper customerMapper;

    public CustomerFacade(Database database)
    {
        this.customerMapper = new CustomerMapper(database);
    }

    public List<Customer> getAllcustomers() throws UserException
    {
        return customerMapper.getAllcustomers();
    }

    public int updateCustomerBalance(int amount, int id) throws UserException
    {
       int rowsAffected = customerMapper.updateCustomerBalance(amount, id);
       return rowsAffected;
    }

}
