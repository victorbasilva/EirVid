/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


package eirvid;
import ConnectDataBase.ConnectH2;
import eirvid.Resource.UserLoad;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victor
 * student number: 2021259
 */
public class EirVid {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        //To do:
        
        /*
        // Scanner
        // While
        1 - Login
        2 - Exit
        
        // Scanner
        // While
        1 - Search movies
        2 - Rent a movie
        3 - 
        */

        //Movie options and prices source
        
        String fileSelected = "C:\\Users\\victo\\OneDrive\\Documents\\NetBeansProjects\\EirVid\\src\\eirvid\\Resource\\Filmes.csv"; 
        
        //Storage of file lines
        
        StringBuilder strBuilderResponse = new StringBuilder();
        
        //File reader

        File file = new File(fileSelected);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")); 
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println(sCurrentLine);
                    strBuilderResponse.append(sCurrentLine);
                    strBuilderResponse.append(System.lineSeparator());
                }
            } catch (IOException e) {
        }
        
        //Movies class instance
        
        Movies movie = new Movies();
        movie.setName("asdf");
        movie.setPrice(BigDecimal.ONE);
        movie.setYear(fileSelected);
        movie.setId(Integer.SIZE);
        
        List<String> line = new ArrayList<>();
        
        //Userload class instance
        
        UserLoad userLoad = new UserLoad();
        List<User> listUsers = userLoad.load();
        
        Rent rent = new Rent();
        rent.setId(1L);
        rent.setName("Matrix");
        rent.setDate(LocalDateTime.now());
        rent.setMovie(movie);
        
        try {
            ConnectH2 coneConnectH2 = new ConnectH2();
            Connection connection = coneConnectH2.getDBConnection(); 
            
            String createSQL = "CREATE TABLE PERSON(id int primary key, name varchar(255))";
            coneConnectH2.createTables(connection, createSQL);
            
            Connection connection1 = coneConnectH2.getDBConnection(); 
            String insertSQL = "INSERT INTO PERSON" + "(id, name) values" + "(1, 'Jeff')";
            coneConnectH2.insertWithStatement(connection1, insertSQL);
            
            Connection connection2 = coneConnectH2.getDBConnection(); 
            String selectSQL = "select * from PERSON";
            coneConnectH2.selectWithPreparedStatement(connection2, selectSQL);
            
            Connection connection3 = coneConnectH2.getDBConnection();
            String updateSQL = "UPDATE PERSON set name = " + "(?)" + " WHERE id = 1";
            coneConnectH2.updateWithPreparedStatement(connection3, updateSQL);
            
            Connection connection4 = coneConnectH2.getDBConnection(); 
            coneConnectH2.selectWithPreparedStatement(connection4, selectSQL);

        } catch (SQLException e) {
        }
    }
}
    

