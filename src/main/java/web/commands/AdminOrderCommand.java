package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminOrderCommand extends CommandProtectedPage
{

    OrderFacade orderFacade;
    public AdminOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        List<Order> orderList = orderFacade.getAllOrders();
        request.setAttribute("orderList", orderList);
        return pageToShow;
    }
}
