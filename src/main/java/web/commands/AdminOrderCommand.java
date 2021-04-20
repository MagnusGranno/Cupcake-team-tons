package web.commands;

import business.entities.Customer;
import business.entities.Order;
import business.entities.OrderWEmail;
import business.exceptions.UserException;
import business.services.CustomerFacade;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminOrderCommand extends CommandProtectedPage
{
    CustomerFacade customerFacade;
    OrderFacade orderFacade;
    public AdminOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {

//        List<Customer> customers = customerFacade.getAllcustomers();
        List<OrderWEmail> orderWEmailList = orderFacade.getAllOrdersWEmail();
        List<Order> orderList = orderFacade.getAllOrders();


        request.setAttribute("orderList", orderList);
        request.setAttribute("orderWEmail", orderWEmailList);
//        request.setAttribute("customers", customers);
        return pageToShow;
    }
}
