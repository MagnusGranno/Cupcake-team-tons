package web.commands;

import business.entities.Cupcake;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.AddItemsMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddItemsToOrder extends Command
{
    public String role;
    public String pageToShow;

    public AddItemsToOrder(String pageToShow, String role)
    {
        this.pageToShow = pageToShow;
        this.role = role;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {

        HttpSession httpSession = request.getSession();
        AddItemsMapper addItemsMapper = new AddItemsMapper(database);

        List<Cupcake> cupcakeList = (List<Cupcake>) httpSession.getAttribute("cartList");
        User user = (User) httpSession.getAttribute("user");

        addItemsMapper.addItemsToOrder(cupcakeList, user);

        httpSession.removeAttribute("cartList");
        httpSession.removeAttribute("total");


        return pageToShow;
    }

    public String getRole()
    {
        return role;
    }
}
