
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pablorivas on 2/5/17.
 */
public class App {
    public static void main(String[] args){
        Connection c = null;
        String connectionDetails = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "nice";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(connectionDetails, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        /*Insert i = new Insert(connectionDetails, username, password);
        try{
            i.insert();
        }   catch (SQLException ex){
            System.out.println(ex.getMessage());
        } */

        Get g = new Get(connectionDetails, username, password);
        try {
            g.get();
        }   catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
