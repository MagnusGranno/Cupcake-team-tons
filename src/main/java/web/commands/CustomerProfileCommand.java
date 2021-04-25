package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.services.CustomerFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CustomerProfileCommand extends CommandProtectedPage
{
    CustomerFacade customerFacade;
    public CustomerProfileCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.customerFacade = new CustomerFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        String userprofile = request.getParameter("userprofile");

        int userbalance = customerFacade.getOldBalance(Integer.parseInt(userprofile));


       List<Order> profileOrderList = customerFacade.getCustomerOrderByCustomerId(Integer.parseInt(userprofile));

       request.setAttribute("balance", userbalance);
        request.setAttribute("profileOrders", profileOrderList);

        return pageToShow;
    }
}
