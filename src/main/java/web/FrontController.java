package web;

import business.exceptions.UserException;
import business.persistence.BottomMapper;
import business.persistence.Database;
import business.persistence.ToppingMapper;
import web.commands.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns = {"/fc/*"})
public class FrontController extends HttpServlet
{

    private final static String USER = "****";
    private final static String PASSWORD = "****";
    private final static String URL = "jdbc:mysql://165.22.92.46:3306/Cupcake?serverTimezone=CET";


    public static Database database;

    public void init() throws ServletException
    {
        // Initialize database connection
        if (database == null)
        {
            try
            {
                database = new Database(USER, PASSWORD, URL);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        BottomMapper bottomMapper = new BottomMapper(database);
        ToppingMapper toppingMapper = new ToppingMapper(database);

        try {
            getServletContext().setAttribute("bottomList", bottomMapper.getAllBottoms());

        } catch (UserException ex) {
            Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
        }

        try {
            getServletContext().setAttribute("toppingList", toppingMapper.getAllToppings());

        } catch (UserException ex) {
            Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
        }

        // Initialize whatever global datastructures needed here:

    }

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            Command action = Command.fromPath(request, database);

            if (action instanceof CommandUnknown) {
                response.sendError(404);
                return;
            }

            String view = action.execute(request, response);

            if (view.startsWith(Command.REDIRECT_INDICATOR)) {
                String page = view.substring(Command.REDIRECT_INDICATOR.length());
                response.sendRedirect(page);
                return;
            }

            request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
        }
        catch (UnsupportedEncodingException | UserException ex)
        {
            request.setAttribute("problem", ex.getMessage());
            Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
            request.getRequestDispatcher("/errorpage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "FrontController for application";
    }

}
