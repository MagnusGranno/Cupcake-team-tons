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

        List<OrderWEmail> orderWEmailList = orderFacade.getAllOrdersWEmail();


        String id = request.getParameter("remove");
        request.setAttribute("orderWEmail", orderWEmailList);
        try
        {

            if (id != null)
            {

                int rowsAffected = orderFacade.deleteOrderById(Integer.parseInt(id));
                if (rowsAffected > 0)
                {
                    orderWEmailList = orderFacade.getAllOrdersWEmail();
                    request.setAttribute("orderWEmail", orderWEmailList);


                }

            }
            request.setAttribute("orderWEmail", orderWEmailList);
            return pageToShow;

        }
        catch(UserException ex)
        {

            request.setAttribute("orderWEmail", orderWEmailList);
            request.setAttribute("error", "mistakes have been made");
            return pageToShow;
        }


    }
}
