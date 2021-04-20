package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.services.CustomerFacade;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCustomerOrderCommand extends CommandProtectedPage
{
    OrderFacade orderFacade;
    CustomerFacade customerFacade;
    public AdminCustomerOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.customerFacade = new CustomerFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {

        String id = request.getParameter("customerid");

        List<Order> customerOrderList = customerFacade.getCustomerOrderByCustomerId(Integer.parseInt(id));
        request.setAttribute("customerOrderList",customerOrderList);

        String email = customerFacade.getCustomerEmailByID(Integer.parseInt(id));
        request.setAttribute("customerEmail",email);

        return pageToShow;
    }
}
