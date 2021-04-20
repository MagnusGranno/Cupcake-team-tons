package web.commands;

import business.entities.Cupcake;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            List<Cupcake> cupcakeList = new ArrayList<>();

            int bottom = Integer.parseInt(request.getParameter("bottom"));
            int topping = Integer.parseInt(request.getParameter("top"));
            int amount = Integer.parseInt(request.getParameter("amount"));
            int price = 0;


            try(Connection connection = database.connect()){

                String sql = "SELECT (SELECT SUM(topping.price) FROM topping WHERE topping_id = ?) + SUM(bottom.price) AS 'total price' FROM bottom WHERE bottom_id = ?;";

                try (PreparedStatement ps = connection.prepareStatement(sql)){

                    ps.setInt(1,topping);
                    ps.setInt(2,bottom);

                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        price = rs.getInt(1);
                    }
                } catch (SQLException e){
                    throw new UserException(e.getMessage());
                }
            } catch (SQLException e){
                throw new UserException(e.getMessage());
            }


            HttpSession httpSession = request.getSession();

            if (httpSession.getAttribute("cartList") == null){
                httpSession.setAttribute("cartList",cupcakeList);
            }

            Cupcake cupcake = new Cupcake(topping,bottom,amount,price);

            List<Cupcake> cupcakeSessionList = (List<Cupcake>) httpSession.getAttribute("cartList");

            cupcakeSessionList.add(cupcake);

            httpSession.setAttribute("cartList",cupcakeSessionList);

            //orderFacade.insertCupcakesIntoDB(cupcake);

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
