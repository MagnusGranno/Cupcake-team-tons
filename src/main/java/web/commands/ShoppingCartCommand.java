package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShoppingCartCommand extends Command
{
    public String role;
    public String pageToShow;

    public ShoppingCartCommand(String pageToShow, String role)
    {
        this.pageToShow = pageToShow;
        this.role = role;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
    {
        return pageToShow;
    }

    public String getRole()
    {
        return role;
    }
}
