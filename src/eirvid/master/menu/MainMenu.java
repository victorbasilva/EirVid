package eirvid.master.menu;

import java.util.Scanner;

/* 
* author: Rafael 
* student number: 2021412 
*/

public class MainMenu {

   public void Menu() {
    
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the EirVid!");
        System.out.println("1 - Login");
        System.out.println("2 - Logout");

        int start = sc.nextInt();  // Read user input

        if (start == 1) {
            System.out.println("The choice is: " + start);
        } else if (start == 2) {
            System.out.println("The choice is: " + start);
        }
        
        System.out.println("Access the System");
        
        Scanner loginUser = new Scanner(System.in);
        System.out.println("Enter your email");
        String email = loginUser.nextLine();  // Read user input

        System.out.println("Your email is: " + email);
        
        Scanner loginPassword = new Scanner(System.in);        
        System.out.println("Enter your password");
        String password = loginPassword.nextLine();  // Read user input

        System.out.println("Your password is: " + password);

    }

   
}
