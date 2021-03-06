package web.commands;

import business.exceptions.UserException;
import business.persistence.Database;

import java.util.HashMap;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command
{
    //Return a token string from the execute method to make a client side redirect,
    // instead of a server side (forward) redirect
    public final static String REDIRECT_INDICATOR = "#*redirect*#_###_";
    public final static String WAS_NOT_FOUND_COMMAND ="404_NOT_FOUND";

    private static HashMap<String, Command> commands;
    public static Database database;

    private static void initCommands(Database database)
    {
        commands = new HashMap<>();
        commands.put("index", new CommandUnprotectedPage("index"));
        commands.put("loginpage", new CommandUnprotectedPage("loginpage"));
        commands.put("logincommand", new LoginCommand("index"));
        commands.put("logoutcommand", new LogoutCommand(""));
        commands.put("registerpage", new CommandUnprotectedPage("registerpage"));
        commands.put("registercommand", new RegisterCommand(""));
//        commands.put("customerpage", new CommandProtectedPage("customerpage", "customer"));
        commands.put("employeepage", new CommandProtectedPage("employeepage", "employee"));

        commands.put("orderpageEmployee", new OrderCommand("orderpage","employee"));
        commands.put("orderpageCustomer", new OrderCommand("orderpage","customer"));
        commands.put("shoppingcart", new ShoppingCartCommand("orderpage","customer"));

        commands.put("cart", new CartCommand("shoppingcartpage","customer"));
        commands.put("cart", new CartCommand("shoppingcartpage","employee"));
        commands.put("addOrder" , new AddItemsToOrder("shoppingcartpage", "customer"));

        commands.put("adminorder", new AdminOrderCommand("adminorderpage","employee"));
        commands.put("admincustomer", new AdminCustomerCommand("admincustomerpage", "employee"));
        commands.put("admincustomerorder", new AdminCustomerOrderCommand("admincustomerorderpage", "employee"));

        commands.put("removeitem", new RemoveItemFromListCommand("shoppingcartpage","customer"));
        commands.put("profile", new CustomerProfileCommand("customerpage", "customer"));



    }

    public static Command fromPath(
            HttpServletRequest request,
            Database db)
    {
        String action = request.getPathInfo().replaceAll("^/+", "");
        System.out.println("--> " + action);

        if (commands == null)
        {
            database = db;
            initCommands(database);
        }

        return commands.getOrDefault(action, new CommandUnknown());   // unknowncommand is default
    }

    public abstract String execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws UserException;

}
