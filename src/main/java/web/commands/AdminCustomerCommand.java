package web.commands;

import business.entities.Customer;
import business.exceptions.UserException;
import business.services.CustomerFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminCustomerCommand extends CommandProtectedPage
{
    CustomerFacade customerFacade;

    public AdminCustomerCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.customerFacade = new CustomerFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        try
        {
            String id = request.getParameter("updatebalance");
            String amount = request.getParameter("amount");

            if (id != null)
            {

                int rowsaffected = customerFacade.updateCustomerBalance(Integer.parseInt(amount), Integer.parseInt(id));
                if (rowsaffected > 0)
                {
                    request.setAttribute("customerList", customerFacade.getAllcustomers());

                }
                else
                {
                    request.setAttribute("error", "Noget gik galt!!");
                }
            }
            request.setAttribute("customerList", customerFacade.getAllcustomers());
            return pageToShow;
        } catch (UserException e)
        {
            e.printStackTrace();
        } catch (NumberFormatException e)
        {
            request.setAttribute("customerList", customerFacade.getAllcustomers());
            request.setAttribute("error","Du kan kun bruge hele tal!!");
            return pageToShow;
        }
        return pageToShow;
    }
}
