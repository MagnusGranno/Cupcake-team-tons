package business.persistence;

import business.entities.Bottom;
import business.entities.Cupcake;
import business.entities.Topping;
import business.entities.User;
import business.exceptions.UserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final static String DATABASE = "Cupcake";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "managesql";
    private final static String PASSWORD = "sql2139";
    private final static String URL = "jdbc:mysql://165.22.92.46:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static UserMapper userMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            userMapper = new UserMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @BeforeEach
    public void setUp() {

            // reset test database
            try ( Statement stmt = database.connect().createStatement() ) {
                stmt.execute("drop table if exists users" );
                stmt.execute("create table " + TESTDATABASE + ".users LIKE " + DATABASE + ".users;" );
                stmt.execute(
                    "insert into users values " +
                    "(1,'jens@somewhere.com','jensen','customer'), " +
                    "(2,'ken@somewhere.com','kensen','customer'), " +
                    "(3,'robin@somewhere.com','batman','employee')");
            } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(database);
    }

    @Test
    public void testLogin01() throws UserException {
        // Can we log in
        User user = userMapper.login( "jens@somewhere.com", "jensen" );
        assertTrue( user != null );
    }

    @Test
    public void testLogin02() throws UserException {
        // We should get an exception if we use the wrong password
        assertThrows(UserException.class, () ->
            {User user = userMapper.login( "jens@somewhere.com", "larsen" ); });

    }

    @Test
    public void testLogin03() throws UserException {
        // Jens is supposed to be a customer
        User user = userMapper.login( "jens@somewhere.com", "jensen" );
        assertEquals( "customer", user.getRole() );
    }

    @Test
    public void testCreateUser01() throws UserException {
        // Can we create a new user - Notice, if login fails, this will fail
        // but so would login01, so this is OK
        User original = new User( "king@kong.com", "uhahvorhemmeligt", "konge" );
        userMapper.createUser( original );
        User retrieved = userMapper.login( "king@kong.com", "uhahvorhemmeligt" );
        assertEquals( "konge", retrieved.getRole() );
    }

    @Test
    public void tester(){
        Topping topping = new Topping(1,"fisk",1);
        Bottom bottom = new Bottom(1,"kage",1);

        List<Cupcake> cupcakeList = new ArrayList<>();
        List<Cupcake> cupcakeList2 = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            Cupcake cupcake = new Cupcake(topping,bottom,i,i);
            cupcakeList.add(cupcake);
        }

        for (int i = 0; i < 100; i++) {

            Cupcake cupcake = new Cupcake(topping,bottom,i,i);
            cupcakeList2.add(cupcake);
        }


        for (Cupcake item:cupcakeList) {

            System.out.println(item.getId());

        }
        for (Cupcake item:cupcakeList2) {

            System.out.println(item.getId());

        }
    }
}
