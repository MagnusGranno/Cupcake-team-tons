package web.commands;

import business.entities.Cupcake;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RemoveItemFromListCommand extends Command
{
    public String role;
    public String pageToShow;

    public RemoveItemFromListCommand(String pageToShow, String role)
    {
        this.pageToShow = pageToShow;
        this.role = role;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {

        HttpSession httpSession = request.getSession();

        List<Cupcake> cupcakeSessionList = (List<Cupcake>) httpSession.getAttribute("cartList");

        Object removeItem = request.getParameter("removeItem");

        if (removeItem != null) {

            cupcakeSessionList.remove(removeItem);

            /*for (Cupcake item: cupcakeSessionList) {
                if(item.toString().hashCode() == removeItem.hashCode()){
                    cupcakeSessionList.remove(item);
                }
            }*/

            httpSession.setAttribute("cartList", cupcakeSessionList);
        }

        return pageToShow;
    }

    public String getRole()
    {
        return role;
    }
}
