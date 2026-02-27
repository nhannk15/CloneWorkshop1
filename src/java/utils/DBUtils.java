package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    public static Connection makeConnection() 
            throws ClassNotFoundException, SQLException{
        //--- Step 1: Load Driver.
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        //--- Step 2: Create Connection String.
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ProductIntro";
        
        //--- Step 3: Open Connection.
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
}
