package web.commands;

import business.entities.Cupcake;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartCommand extends Command
{
    public String role;
    public String pageToShow;
    private OrderFacade orderFacade;

    public ShoppingCartCommand(String pageToShow, String role)
    {
        this.pageToShow = pageToShow;
        this.role = role;
        this.orderFacade = new OrderFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        try{

            int bottom = Integer.parseInt(request.getParameter("bottom"));
            int topping = Integer.parseInt(request.getParameter("top"));
            int amount = Integer.parseInt(request.getParameter("amount"));

            /*request.setAttribute("bottom",bottom);
            request.setAttribute("topping",topping);
            request.setAttribute("amount",amount);*/

            Cupcake cupcake = new Cupcake(topping,bottom,amount);

            /*request.setAttribute("cupcakeList",cupcakeList);*/

            orderFacade.insertCupcakesIntoDB(cupcake);

        } catch (NumberFormatException e){
            throw new UserException(e.getMessage());
        }

        return pageToShow;
    }

    public String getRole()
    {
        return role;
    }
}
