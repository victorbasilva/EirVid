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
        
        // Creating all tables in the H2 database in memory
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        Statement stmt;
        try (connection) {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            // Create a UserEirVid table 
            stmt.execute("CREATE TABLE IF NOT EXISTS USEREIRVID(id IDENTITY NOT NULL PRIMARY KEY, emailUser VARCHAR(200), nameUser VARCHAR(150), passwordUser VARCHAR(50))");
            stmt.execute("INSERT INTO USEREIRVID(emailUser, nameUser, passwordUser) VALUES('angelita@gmail.com', 'Angelita', '123456')");

            // Create a Movie table 
            stmt.execute("CREATE TABLE IF NOT EXISTS MOVIE(id IDENTITY NOT NULL PRIMARY KEY, nameMovie VARCHAR(200), categoryMovie VARCHAR(150), yearMovie VARCHAR(4), price VARCHAR(6))");

            
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
    
    // Method to create a table
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
    
    // Insert a new user in the system
    public void insertNewUserSql(String nameUser, String emailUser, String passwordUser, String insertNewUserSql) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();

        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(insertNewUserSql);
            pstm.setString(1, nameUser);
            pstm.setString(2, emailUser);
            pstm.setString(3, passwordUser);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             connectToDataBase.closeConnection(connection, null, null);
        }
    }
    
    // Insert a user after created the table MOVIE
    public void insertSql(String nameMovie, String categoryMovie, String yearMovie, String price, String insertSqlMovie) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();

        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(insertSqlMovie);
            pstm.setString(1, nameMovie);
            pstm.setString(2, categoryMovie);
            pstm.setString(3, yearMovie);
            pstm.setString(4, price);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             connectToDataBase.closeConnection(connection, null, null);
        }
    }
    
    // Insert using sqj value
    public void insertSqlWithoutPreparedStatement(String sql) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        ResultSet rs  = null;
        Statement stmt;
        try (connection) {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
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
    
    // Update using sql value
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

    // Get the data of Movie using an id
    public Movie findMovieById(Long idMovie) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        PreparedStatement selectPreparedStatement = null;

        Movie movie = new Movie();
        ResultSet rs = null;
        String SelectQuery = "SELECT * FROM MOVIE WHERE ID = ?";

        try (connection) {
            connection.setAutoCommit(false);

            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setLong(1, idMovie);
            
            rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                movie.setId(rs.getLong("id"));
                movie.setNameMovie(rs.getString("nameMovie"));
                movie.setCategoryMovie(rs.getString("categoryMovie"));
                movie.setYearMovie(rs.getString("yearMovie"));
                movie.setPrice(rs.getString("price"));
            } 
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
        } finally {
            System.out.println("Database Selected");
            connectToDataBase.closeConnection(connection, selectPreparedStatement, rs);
            return movie;
        }
    }    
    
    // Found a user in a database
    public UserEirVid findUserById(Long idUser) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        PreparedStatement selectPreparedStatement = null;

        UserEirVid user = new UserEirVid();
        ResultSet rs = null;
        String SelectQuery = "SELECT * FROM USEREIRVID WHERE ID = ?";

        try (connection) {
            connection.setAutoCommit(false);

            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setLong(1, idUser);
            
            rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setEmailUser(rs.getString("emailUser"));
                user.setNameUser(rs.getString("nameUser"));
                user.setPasswordUser(rs.getString("passwordUser"));
            } 
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
        } finally {
            System.out.println("Database Selected");
            connectToDataBase.closeConnection(connection, selectPreparedStatement, rs);
            return user;
        }
    }
    
    // Verify if the user exist in a database
    public boolean checkIfUserExists(String email, String password) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        PreparedStatement selectPreparedStatement = null;

//        UserEirVid user = new UserEirVid();
        boolean userFound = false;
        ResultSet rs = null;
//        String sql = "SELECT * FROM UserEirVid WHERE EMAIL = ? AND PASSWORD = ?";
        String SelectQuery = "SELECT * FROM USEREIRVID WHERE EMAILUSER = ? AND PASSWORDUSER = ?";

        try (connection) {
            connection.setAutoCommit(false);

            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setString(1, email);
            selectPreparedStatement.setString(2, password);
            
            rs = selectPreparedStatement.executeQuery();
            if(rs.next()) {
                userFound = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
        } finally {
            System.out.println("Database Selected");
            connectToDataBase.closeConnection(connection, selectPreparedStatement, rs);
            return userFound;
        }
    }
}
