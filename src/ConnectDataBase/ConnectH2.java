/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Victor Silva
 */
public class ConnectH2 {
    
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    
    public void createTables(Connection connection, String sql) throws SQLException {
        PreparedStatement createPreparedStatement;
        String CreateQuery = sql;

        try (connection) {
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
         
            connection.commit();
            connection.close();
            System.out.println("Database Created");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
    }    
    
    public void insertWithStatement(Connection connection, String sql) throws SQLException {
            Statement stmt;
        try (connection) {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute(sql);

            ResultSet rs = stmt.executeQuery("select * from PERSON");
            System.out.println("H2 In-Memory Database inserted through Statement");
            while (rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            }

            //stmt.close();
            
            connection.commit();
            System.out.println("Database Inserted");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }    
    
    public void updateWithPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement updatePreparedStatement;

        String UpdateQuery = sql;

        try (connection) {
            connection.setAutoCommit(false);
            
            updatePreparedStatement = connection.prepareStatement(UpdateQuery);
            updatePreparedStatement.setString(1, "Carter");
            updatePreparedStatement.executeUpdate();
            updatePreparedStatement.close();
            
            connection.commit();
            connection.close();
            System.out.println("Database Updated");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
        }
    }
    
    public void selectWithPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement selectPreparedStatement;

        String SelectQuery = sql;

        try (connection) {
            connection.setAutoCommit(false);

            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs= selectPreparedStatement.executeQuery();
            System.out.println("H2 In-Memory Database inserted through PreparedStatement");
            while (rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            }
            selectPreparedStatement.close();             

            connection.commit();
            connection.close();
            System.out.println("Database Selected");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
        }
    }    
    
    public Connection getDBConnection() {
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
    
        
//    public static void insertWithPreparedStatement(Connection connection, String sql) throws SQLException {
//        PreparedStatement insertPreparedStatement;
//
//        String InsertQuery = sql;
//
//        try {
//            connection.setAutoCommit(false);
//
//            insertPreparedStatement = connection.prepareStatement(InsertQuery);
////            insertPreparedStatement.setInt(1, 1);
////            insertPreparedStatement.setString(2, "Jose");
////            insertPreparedStatement.executeUpdate();
//            insertPreparedStatement.close();
//
//            connection.commit();
//        } catch (SQLException e) {
//            System.out.println("Exception Message " + e.getLocalizedMessage());
//        } catch (Exception e) {
//        } finally {
////            connection.close();
//        }
//    }
    
}
