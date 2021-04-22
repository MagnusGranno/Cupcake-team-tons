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

        int removeItem = Integer.parseInt(request.getParameter("removeItem"));

        int removeID = existsInList(cupcakeSessionList,removeItem);

        cupcakeSessionList.remove(removeID);

        httpSession.setAttribute("cartList", cupcakeSessionList);

        int total = 0;
        for (Cupcake item : cupcakeSessionList) {
            total += item.getPrice();
        }
        httpSession.setAttribute("total",total);

        if (cupcakeSessionList.isEmpty()){
            httpSession.removeAttribute("total");
        }

        return pageToShow;
    }

    public int existsInList(List<Cupcake> cupcakeList, int id){

        for (int i = 0; i < cupcakeList.size(); i++) {
            if (id == cupcakeList.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }



    public String getRole()
    {
        return role;
    }
}
