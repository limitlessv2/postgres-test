
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by pablorivas on 2/8/17.
 */

public class Insert {
    public String connection;
    public String username;
    public String password;

    public Insert (String con, String u, String p)
    {
        this.connection = con;
        this.username = u;
        this.password = p;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(connection, username, password);
    }

    public void insert() throws SQLException {
        String SQL = "{call PR_TripProfile_PUT(?,?,?)}";

        try{
            Connection conn = connect();
            java.util.Date now = new java.util.Date();
            CallableStatement stmnt =  conn.prepareCall(SQL);
            stmnt.setString(1, "JUPITER");
            stmnt.setString(2, "300");
            stmnt.setTimestamp(3, new java.sql.Timestamp(now.getTime()), java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("GMT+00:00")));
            stmnt.execute();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("Insert successfully");
    }
}
