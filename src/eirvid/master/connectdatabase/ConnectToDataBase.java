package eirvid.master.connectdatabase;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Victor Silva
 */

public class ConnectToDataBase {
    
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "SA";
    private static final String DB_PASSWORD = "123456";
        
    public Connection getConnection() {
        
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("Connection error with database: " + e.getMessage());
        }
        return dbConnection;
    }
    
    public void closeConnection(Connection conn, PreparedStatement pstm, ResultSet rs)
    {
        try {
            if (conn != null) {
                conn.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
               rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
