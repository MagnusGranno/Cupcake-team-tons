package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderCommand extends Command
{
    public String role;
    public String pageToShow;

    public OrderCommand(String pageToShow, String role) {

        this.pageToShow = pageToShow;
        this.role = role;
    }

    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) throws UserException
    {



        return pageToShow;
    }
    public String getRole() { return role; }
}


