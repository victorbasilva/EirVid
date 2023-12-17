package eirvid.master;

import eirvid.master.connectdatabase.AccessH2;
import eirvid.master.model.Movie;
import eirvid.master.model.UserEirVid;
import eirvid.master.model.dto.RentDto;
import eirvid.master.resource.ReadCSVFile;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                    System.out.println("--- 1 --- Login");
                    userLoged = login(accessH2);

                    if (userLoged > 0) {
                        user = findUserBy(accessH2, userLoged);
                    }
                }
                case 2 -> {
                    System.out.println("--- 2 --- Rent your movie");
                    if (userLoged > 0) {
                        choiceMovie(user.getId(), csv, accessH2);
                    } else {
                        System.out.println("Please, enter your login");
                    }
                }
                case 3 -> {
                        System.out.println("--- 3 --- Create a new user");
                        createUser(accessH2);
                }
                case 4 -> {
                    System.out.println("--- 4 --- My movie rentals today");
                    showMoviesToday(accessH2);
                }
                case 5 -> {
                    System.out.println("--- 5 --- Show the last 5 rentals");
                    showLastFiveRentals(accessH2);
                }
                case 6 -> {
                    System.out.println("--- 6 --- Exiting the program. Goodbye!");
                    finish();
                }
            }
        } while (choice != 6);
        scanner.close();
    }

    //Show a screen menu for the user
    private static void displayMenu() {
        System.out.println("=== Console Menu ===");

        System.out.println("1 - Login");
        System.out.println("2 - Rent your movie");
        System.out.println("3 - Create your user");
        System.out.println("4 - My movie rentals today");
        System.out.println("5 - Show the last 5 rentals");
        System.out.println("6 - Logout");

        System.out.println("====================");
    }
    
    //Fininsh the applicattion
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
        Long userFound;
        
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
            userFound = 0L;
            System.out.println("User and password Incorrect.");
        }
        return userFound;
    }
    
    //Chosed movies database storage
    private static void choiceMovie(Long userId, ReadCSVFile csv, AccessH2 accessH2) {
        
        Scanner sc = new Scanner(System.in);

        Long search;
        Long movieRented;
        
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
        System.out.println("1 - Choose your movie");
        System.out.println("2 - Back");

        while (!sc.hasNextLong()) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
            }
        search = sc.nextLong();

        if (search == 1) {
                System.out.println("Type Id to choose your Movie.");
                while (!sc.hasNextLong()) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next();
                    }
                search = sc.nextLong();
                
                Movie movie = accessH2.findMovieById(search);
                movieRented = movie.getId();

                System.out.print("Id: " + movie.getId());
                System.out.print(" - Name: " + movie.getNameMovie());
                System.out.print(" - Category: " + movie.getCategoryMovie());
                System.out.print(" - Year: " + movie.getYearMovie());
                System.out.println(" - Price: " + movie.getPrice());

                System.out.println( "###################################### RENT ###############################################");

                String insertSqlMovie = "INSERT INTO RENT(userRent, movieRent, dateRent) VALUES(?, ?, ?)";

                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formatDateTime = date.format(formatter);

                accessH2.insertMovieAndUserInRentSql(userId, movieRented, formatDateTime, insertSqlMovie);
                
                System.out.println("Movie rented.");
            } else if (search == 2) {
        }
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
        System.out.println("2 - Back");
//        start = sc.nextInt();  // Read user input
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
            }
        start = sc.nextInt();

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

        System.out.println("User " + createdUser.getNameUser() + " created.");
    }

    //5 last movies rented 
    private static void showLastFiveRentals(AccessH2 accessH2) {
        
        List<RentDto> rentals = accessH2.showLastFiveRentals();

        if (!rentals.isEmpty()) {
            for (RentDto rent : rentals) {
                System.out.print(" - Id: " + rent.getId());
                System.out.print(" - Name: " + rent.getNameMovie());
                System.out.print(" - Category: " + rent.getCategoryMovie());
                System.out.println(" - Year: " + rent.getYearMovie());
            }
        } else {
            System.out.println("There is no movie rented yet.");              
        }
    }
    
    private static void showMoviesToday(AccessH2 accessH2) {
        
        List<RentDto> rentals = accessH2.showMoviesToday();
            
        if (!rentals.isEmpty()) {
            for (RentDto rent : rentals) {
                System.out.print(" - Name: " + rent.getNameMovie());
                System.out.print(" - Category: " + rent.getCategoryMovie());
                System.out.println(" - Year: " + rent.getYearMovie());
            }
        } else {
            System.out.println("There is no movie rented yet.");
        }
    }  
}