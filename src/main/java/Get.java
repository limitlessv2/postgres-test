import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.*;
import java.util.*;

/**
 * Created by pablorivas on 2/9/17.
 */
public class Get {

    public String connection;
    public String username;
    public String password;

    public Get (String con, String u, String p)
    {
        this.connection = con;
        this.username = u;
        this.password = p;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(connection, username, password);
    }

    public void get() throws SQLException {
        String SQL = "{call PR_TripProfileByCity_GET(?)}";

        try{
            Connection conn = connect();
            java.util.Date now = new java.util.Date();
            CallableStatement stmnt =  conn.prepareCall(SQL);
            stmnt.setString(1, "JUPITER");
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()){
                String city = rs.getString("City");
                String price = rs.getString("Price");
                java.util.Date travelDate = rs.getTimestamp("TravelDate");
                Boolean prospect = rs.getBoolean("Prospect");
                Boolean deal = rs.getBoolean("Deal");
                Boolean trash = rs.getBoolean("Trash");
                java.util.Date createdDate = rs.getTimestamp("CreatedDate");
                String createdBy = rs.getString("CreatedBy");
                java.util.Date updatedDate = rs.getTimestamp("UpdatedDate");
                String updatedBy = rs.getString("UpdatedBy");

                System.out.println("City : " + city);
                System.out.println("Price : " + price);
                System.out.println("TravelDate : " + travelDate);
                System.out.println("Prospect : " + prospect);
                System.out.println("Deal : " + deal);
                System.out.println("Trash : " + trash);
                System.out.println("CreatedDate : " + createdDate);
                System.out.println("CreatedBy : " + createdBy);
                System.out.println("UpdatedDate : " + updatedDate);
                System.out.println("UpdatedBy : " + updatedBy);

                rs.close();
                stmnt.close();
                conn.close();
                /*City 			TEXT 	  ,
                Price   		TEXT	  ,
                TravelDate	 	TIMESTAMP ,
                Prospect		BOOLEAN   ,
                Deal			BOOLEAN   ,
                Trash			BOOLEAN   ,
                CreatedDate 	TIMESTAMP ,
                CreatedBy		TEXT 	  ,
                UpdatedDate 	TIMESTAMP ,
                UpdatedBy		TEXT
                 */
            }



        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("Get call was successful");
    }
}
