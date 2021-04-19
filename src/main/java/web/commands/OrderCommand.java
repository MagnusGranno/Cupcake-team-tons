package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderCommand extends Command
{
    public String pageToShow;

    public OrderCommand(String pageToShow)
    {
        this.pageToShow = pageToShow;
    }

    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) throws UserException
    {
        return pageToShow;
    }
}


