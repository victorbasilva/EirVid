package eirvid.master;

import eirvid.master.connectdatabase.AccessH2;
import eirvid.master.model.Movie;
import eirvid.master.model.UserEirVid;
import eirvid.master.model.dto.RentDto;
import eirvid.master.resource.ReadCSVFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * @author victor
 * student number: 2021259
 */

public class EirVidMaster {

 public static void main(String[] args) {

        //Instance of database class
        AccessH2 accessH2 = new AccessH2();
        accessH2.createTables();
        
        //Read database from movies file
        ReadCSVFile csv = new ReadCSVFile();
        csv.readCsv();
        
        int choice;
        Long userLoged = 0L;
        UserEirVid user = new UserEirVid();
        List<Movie> movies = new ArrayList<>();
        List<RentDto> movieRented = new ArrayList<>();

        //Enter into the screen menu
        Scanner scanner = new Scanner(System.in);
        
        do {
            displayMenu();
            System.out.print("Enter your choice (1-4, 5 to exit): ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1 -> {
                    System.out.println("1 - Login");
                    userLoged = login(accessH2);
                    if (userLoged != null && userLoged > 0) {
                        user = findUserBy(accessH2, userLoged);
                    }
                }
                case 2 -> {
                    System.out.println("2 - Rent your movie");
                    if (userLoged != null && userLoged > 0) {
                        movies = choiceMovie(user.getId(), csv, accessH2);
                    } else {
                        System.out.println("Please, enter your login");
                    }
                }
                case 3 -> {
                    if (userLoged == null || userLoged == 0) {
                        System.out.println("Exit to create a new user.");
                    } else {
                        System.out.println("3 - Create your user");
                        createUser(accessH2);
                    }
                }
                case 4 -> {
                    System.out.println("4 - Show the last 5 rentals");
                    movieRented = showLastFiveRentals(accessH2);
                }
                case 5 -> {
                    System.out.println("4 - Exiting the program. Goodbye!");
                    finish();
                }
            }
        } while (choice != 5);
        scanner.close();
    }
    
    //Show a screen menu for the user
    private static void displayMenu() {
        System.out.println("=== Console Menu ===");

        System.out.println("1 - Login");
        System.out.println("2 - Rent your movie");
        System.out.println("3 - Create your user");
        System.out.println("4 - Show the last 5 rentals");
        System.out.println("5 - Logout");

        System.out.println("====================");
    }

    private static void finish() {
        
        System.out.println("Logout of Application");
        System.exit(0); 
    }
    
    private static UserEirVid findUserBy(AccessH2 accessH2, Long userLoged) {
         
        return accessH2.findUserById(userLoged);
    }
    
    //Pick and analyse the user's input information
    private static Long login(AccessH2 accessH2) {
        
        String email;
        String password;
        Scanner emailUser;
        Scanner passwordUser;
        Long userFound = 0L;
        
        System.out.println("Access the System");

        emailUser = new Scanner(System.in);
        System.out.println("Enter your email");
        email = emailUser.nextLine();  // Read user input jeff@gmail.com

        passwordUser = new Scanner(System.in);
        System.out.println("Enter your password");
        password = passwordUser.nextLine();  // Read user input

        userFound = accessH2.checkIfUserExists( email, password);
        if (userFound != null) {
            System.out.println("User and password Correct.");
        } else {
            System.out.println("User and password Incorrect.");
        }
        return userFound;
    }
    
    //Chosed movies database storage
    private static List<Movie> choiceMovie(Long userId, ReadCSVFile csv, AccessH2 accessH2) {
        
        Long search;
        Long movieRented;
        
        Scanner sc = new Scanner(System.in);
        List<Movie> movies;
        movies = csv.listMovies();
        
        for (Movie movie : movies) {
            System.out.print("Id: " + movie.getId());
            System.out.print(" - Name: " + movie.getNameMovie());
            System.out.print(" - Category: " + movie.getCategoryMovie());
            System.out.print(" - Year: " + movie.getYearMovie());
            System.out.println(" - Price: " + movie.getPrice());
        }

        System.out.println("Choose your movie.");
        System.out.println("1 - Search the movies");
        System.out.println("2 - Back");
        search = sc.nextLong();  // Read user input

        if (search == 1) {
                System.out.println("Type Id to choice your Movie.");
                search = sc.nextLong();  // Read user input
                Movie movie = accessH2.findMovieById(search);
                movieRented = movie.getId();

                System.out.print("Id: " + movie.getId());
                System.out.print(" - Name: " + movie.getNameMovie());
                System.out.print(" - Category: " + movie.getCategoryMovie());
                System.out.print(" - Year: " + movie.getYearMovie());
                System.out.println(" - Price: " + movie.getPrice());

                System.out.println( "###################################### RENT ###############################################");

                String insertSqlMovie = "INSERT INTO RENT(nameRent, movieRent, dateRent) VALUES(?, ?, ?)";

                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formatDateTime = date.format(formatter);

                accessH2.insertMovieAndUserInRentSql(userId, movieRented, formatDateTime, insertSqlMovie);

                movies.add(movie);
                System.out.println("Movie rented.");
            } else if (search == 2) {
        return null;              
        }
        return movies;
    }
    
    //New user creation
    private static void createUser(AccessH2 accessH2) {
        
        String name;
        String email;
        String password;
        Scanner nameUser;
        Scanner emailUser;
        Scanner passwordUser;
        int start;
        
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Create your user");
        System.out.println("2 - Finish");
        start = sc.nextInt();  // Read user input

        if (start == 2) {
            System.out.println("Logout of Application");
            System.exit(0); 
        }

        nameUser = new Scanner(System.in);
        System.out.println("Enter your name");
        name = nameUser.nextLine();

        emailUser = new Scanner(System.in);
        System.out.println("Enter your email");
        email = emailUser.nextLine();

        passwordUser = new Scanner(System.in);
        System.out.println("Enter your password");
        password = passwordUser.nextLine();

        String insertNewUserSql = "INSERT INTO USEREIRVID(nameUser, emailUser, passwordUser) VALUES (?, ?, ?)";
        accessH2.insertNewUserSql(name, email, password, insertNewUserSql);

        UserEirVid createdUser = accessH2.findUserById(2L);

        System.out.print("Id: " + createdUser.getId());
        System.out.print(" - Name: " + createdUser.getNameUser());
        System.out.print(" - Email: " + createdUser.getEmailUser());
        System.out.println(" - Password: " + createdUser.getPasswordUser());
        System.out.println();
        System.out.print("User created with success.");
    }
    
    //5 last movies rented 
    private static List<RentDto> showLastFiveRentals(AccessH2 accessH2) {
        
        List<RentDto> rentals = new ArrayList<>();
        
        if (rentals.isEmpty()) {
            rentals = accessH2.showLastFiveRentals();
        } else {
            System.out.print("There is no movie rented yet.");              
        }

        for (RentDto rent : rentals) {
            System.out.print(" - Name: " + rent.getNameMovie());
            System.out.print(" - Category: " + rent.getCategoryMovie());
            System.out.println(" - Year: " + rent.getYearMovie());
        }
        return rentals;
    }  
}