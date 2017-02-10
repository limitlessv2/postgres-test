import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pablorivas on 2/9/17.
 */
public class Update {
    public String connection;
    public String username;
    public String password;

    public Update (String con, String u, String p)
    {
        this.connection = con;
        this.username = u;
        this.password = p;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(connection, username, password);
    }

    public void update() throws SQLException {
        String SQL = "{call PR_TripProfileByCityDeal_UPDATE(?)}";

        try{
            Connection conn = connect();
            CallableStatement stmnt =  conn.prepareCall(SQL);
            stmnt.setString(1, "JUPITER");
            stmnt.execute();

            stmnt.close();
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("Update successful");
    }
}
