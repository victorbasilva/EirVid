package eirvid.master;

import eirvid.master.connectdatabase.AccessH2;
import eirvid.master.model.Movies;
import eirvid.master.model.Rent;
import eirvid.master.model.UserEirVid;
import eirvid.master.resource.UserLoad;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * @author victor
 * student number: 2021259
 */

public class EirVidMaster {

 public static void main(String[] args) {

        AccessH2 accessH2 = new AccessH2();
        accessH2.createTables();
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the EirVid!");
        System.out.println("1 - Login");
        System.out.println("2 - Logout");
        int start = sc.nextInt();  // Read user input
        
        while (start == 1) {
            if (start == 1) {
                UserEirVid user = null;
                
                System.out.println("Access the System");

                Scanner loginUser = new Scanner(System.in);
                System.out.println("Enter your email");
                String email = loginUser.nextLine();  // Read user input

                System.out.println("Your email is: " + email);

                Scanner loginPassword = new Scanner(System.in);
                System.out.println("Enter your password");
                String password = loginPassword.nextLine();  // Read user input

                System.out.println("Your password is: " + password);

                String sql = "SELECT * FROM UserEirVid WHERE EMAIL = ? AND PASSWORD = ?";
                
                boolean userFound = accessH2.SelectUser(sql, email, password);
                if (userFound) {
                    System.out.println("User found");
//                    System.out.println("Result of consult: " + user.getId() + ", " + user.getEmail() + ", " +  user.getName() + ", " +  user.getPassword());
                } else {
                    System.out.println("User not found");
                }
            
            } else if (start == 2) {
                System.out.println("Logout of the Application: " + start);
            }
            System.out.println("Welcome to the EirVid!");
            System.out.println("1 - Login");
            System.out.println("2 - Logout");
            start = sc.nextInt();  // Read user input
        }
        System.exit(0);

        // PATH to file .csv
        String selectedFile = "resources/movies.csv";

        // Variable that will receive the content of the file
        StringBuilder strBuilderResponse = new StringBuilder();

        // Reading the file
        File file = new File(selectedFile);
        try {
            // Buffer that will read the file
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            // Variable that will receive each line of the file
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                strBuilderResponse.append(sCurrentLine);
            }
        } catch (IOException e) {
        }

        Movies movie = new Movies();
        movie.setName("asdf");
        movie.setPrice(BigDecimal.ONE);
        movie.setYear(selectedFile);
        movie.setId(Integer.SIZE);

        List<String> line = new ArrayList<>();

        UserLoad userLoad = new UserLoad();
        List<UserEirVid> listUsers = userLoad.load();

        Rent rent = new Rent();
        rent.setId(1L);
        rent.setName("Matrix");
        rent.setDate(LocalDateTime.now());
        rent.setMovie(movie);
    }  
}