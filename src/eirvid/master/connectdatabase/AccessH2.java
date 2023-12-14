package eirvid.master.connectdatabase;

import eirvid.master.model.UserEirVid;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alessandracaballero
 * student number: 2021258
 */


public class AccessH2 {

    public void createTables() {
        
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        Statement stmt;
        try (connection) {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS UserEirVid(id int primary key, email varchar(200), name varchar(150), password varchar(50))");
            stmt.execute("INSERT INTO UserEirVid(id, email, name, password) VALUES(1, 'angelita@gmail.com', 'Angelita', '123456')");

            stmt.close();
            connection.commit();
            System.out.println("Databases Created");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectToDataBase.closeConnection(connection, null, null);
        }
    }
    
    public void createTable(String sql) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        PreparedStatement createPreparedStatement = null;
        String CreateQuery = sql;

        try (connection) {
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();

            connection.commit();
            System.out.println("Database Created");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } finally {
            connectToDataBase.closeConnection(connection, createPreparedStatement, null);
        }
    }

    public void insertWithStatement(String sql) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        ResultSet rs  = null;
        Statement stmt;
        try (connection) {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute(sql);

            rs = stmt.executeQuery("select * from USER");
            System.out.println("H2 In-Memory Database inserted through Statement");
//            while (rs.next()) {
//                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
//            }
            stmt.close();
            connection.commit();
            System.out.println("Database Inserted");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectToDataBase.closeConnection(connection, null, rs);
        }
    }

    public void updateWithPreparedStatement(String sql) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        PreparedStatement updatePreparedStatement = null;

        String UpdateQuery = sql;

        try (connection) {
            connection.setAutoCommit(false);

            updatePreparedStatement = connection.prepareStatement(UpdateQuery);
            updatePreparedStatement.setString(1, "Carter");
            updatePreparedStatement.executeUpdate();

            connection.commit();
            System.out.println("Database Updated");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
        } finally {
            connectToDataBase.closeConnection(connection, updatePreparedStatement, null);            
        }
    }

    public boolean SelectUser(String sqlUser, String email, String password) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        PreparedStatement selectPreparedStatement = null;

        UserEirVid user = new UserEirVid();
        boolean userFound = false;
        ResultSet rs = null;
        String SelectQuery = sqlUser;

        try (connection) {
            connection.setAutoCommit(false);

            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setString(1, email);
            selectPreparedStatement.setString(2, password);
            
            rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                userFound = true;
            } 
//            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
        } finally {
            System.out.println("Database Selected");
            connectToDataBase.closeConnection(connection, selectPreparedStatement, rs);
        }
        return userFound;
    }
}