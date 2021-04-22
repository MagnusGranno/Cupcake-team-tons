package web.commands;

import business.entities.Cupcake;
import business.exceptions.UserException;
import business.services.CartFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CartCommand extends Command
{
    public String role;
    public String pageToShow;
    private CartFacade cartFacade;


    public CartCommand(String pageToShow, String role)
    {
        this.pageToShow = pageToShow;
        this.role = role;
        this.cartFacade = new CartFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {



        return pageToShow;
    }

    public String getRole()
    {
        return role;
    }
}
