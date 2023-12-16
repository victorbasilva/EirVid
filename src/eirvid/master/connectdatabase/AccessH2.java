package eirvid.master.connectdatabase;

import eirvid.master.model.Movie;
import eirvid.master.model.Rent;
import eirvid.master.model.UserEirVid;
import eirvid.master.model.dto.RentDto;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alessandracaballero
 * student number: 2021258
 */


public class AccessH2 {

    // Method to create a tables of system
    public void createTables() {
        
        
        //Creating all tables in H2 database in memory
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        Statement stmt;
        try (connection) {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            
            // Create a UserEirVid table 
            
            stmt.execute("CREATE TABLE IF NOT EXISTS USEREIRVID (" + 
                "id IDENTITY NOT NULL PRIMARY KEY, " +
                "emailUser VARCHAR(200), " + 
                "nameUser VARCHAR(150), " + 
                "passwordUser VARCHAR(50))"
            );

            stmt.execute("CREATE TABLE IF NOT EXISTS MOVIE (" + 
                "id IDENTITY NOT NULL PRIMARY KEY, " + 
                "nameMovie VARCHAR(200), " + 
                "categoryMovie VARCHAR(150), " +  
                "yearMovie VARCHAR(4), " + 
                "price VARCHAR(6))"
            );

            stmt.execute("CREATE TABLE IF NOT EXISTS RENT (" +
                "id IDENTITY NOT NULL PRIMARY KEY, " +
                "nameRent INT NOT NULL, " +
                "movieRent INT NOT NULL, " +
                "dateRent VARCHAR(19), " +
                "FOREIGN KEY(id) " +
                "REFERENCES RENT (id), " +
                "FOREIGN KEY(id) " +
                "REFERENCES RENT (id))"
            );
            
            stmt.execute("INSERT INTO USEREIRVID(emailUser, nameUser, passwordUser) VALUES('jeff@gmail.com', 'Jeff', '123456')");
            stmt.execute("INSERT INTO RENT(nameRent, movieRent, dateRent) VALUES(1, 1, '2023-12-10 12:34:00')");
//            stmt.execute("INSERT INTO MOVIE(nameMovie, categoryMovie, yearMovie, price) VALUES('Matrix', 'Sifi', '1999', '5.00')");
            
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
    
    // Insert a movie and an user in the RENT table
    public void insertMovieAndUserInRentSql(Long nameRent, Long movieRent, String dateRent, String insertMovieAndUserInRentSql) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();

        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(insertMovieAndUserInRentSql);
            pstm.setLong(1, nameRent);
            pstm.setLong(2, movieRent);
            pstm.setString(3, dateRent);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             connectToDataBase.closeConnection(connection, null, null);
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

    public List<RentDto> showLastFiveRentals() {
        
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        PreparedStatement selectPreparedStatement = null;

        List<RentDto> renteds = new ArrayList<>();
        ResultSet rs = null;
        
        String selectQuery = "SELECT movieRent.NAMEMOVIE, movieRent.CATEGORYMOVIE, movieRent.YEARMOVIE from RENT " +
                             "JOIN USEREIRVID userRent " +
                             "ON RENT.ID = userRent.ID " +
                             "JOIN MOVIE movieRent " +
                             "ON userRent.ID = movieRent " +
                             "ORDER BY userRent.ID DESC LIMIT 5";
        
        RentDto rentDto = null;
        try {
            selectPreparedStatement = connection.prepareStatement(selectQuery);
            rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                rentDto = new RentDto();
                rentDto.setNameMovie(rs.getString("nameMovie"));
                rentDto.setCategoryMovie("categoryMovie");
                rentDto.setYearMovie(rs.getString("yearMovie"));

                renteds.add(rentDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectToDataBase.closeConnection(connection, selectPreparedStatement, rs);
        }
        return renteds;
    }
    
    public List<Rent> findAllRent() {
        
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        PreparedStatement selectPreparedStatement = null;

        List<Rent> renteds = new ArrayList<>();
        ResultSet rs = null;
        String selectQuery = "SELECT * FROM RENT";
        
        Rent rent = null;
        try {
            selectPreparedStatement = connection.prepareStatement(selectQuery);
            rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                rent = new Rent();
                rent.setId(rs.getLong("id"));
                rent.setMovieRent(rs.getLong("movieRent"));
                rent.setNameRent(rs.getLong("userRent"));
                rent.setdateRent(rs.getString("dateRent"));

                renteds.add(rent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectToDataBase.closeConnection(connection, selectPreparedStatement, rs);
        }
        return renteds;
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
            connectToDataBase.closeConnection(connection, selectPreparedStatement, rs);
        }
        return user;
    }
    
    // Verify if the user exist in a database
    public Long checkIfUserExists(String email, String password) {
                
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();
        Connection connection = connectToDataBase.getConnection();
        
        PreparedStatement selectPreparedStatement = null;

//        UserEirVid user = new UserEirVid();
//        boolean userFound = false;
        UserEirVid user = new UserEirVid();
        ResultSet rs = null;

        String SelectQuery = "SELECT * FROM USEREIRVID WHERE EMAILUSER = ? AND PASSWORDUSER = ?";

        try (connection) {
            connection.setAutoCommit(false);

            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setString(1, email);
            selectPreparedStatement.setString(2, password);
            
            rs = selectPreparedStatement.executeQuery();
            if(rs.next()) {
                user.setId(rs.getLong("id"));
//                userFound = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
        } finally {
            connectToDataBase.closeConnection(connection, selectPreparedStatement, rs);
        }
        return user.getId();
    }
}
