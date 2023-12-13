package eirvid.master.resource;

import eirvid.master.model.UserEirVid;
import java.util.ArrayList;
import java.util.List;

/*
*Student Name: Joelma Rodrigues
*Student ID: 2023246
*/

public class UserLoad {

    // Method to load the users
    public List<UserEirVid> load() {

        // List of users
        List<UserEirVid> listUsers = new ArrayList<>();

        // Instances of user
        UserEirVid user1 = new UserEirVid();
        UserEirVid user2 = new UserEirVid();
//        User user3 = new User();
//        User user4 = new User();
//        User user5 = new User();
//        User user6 = new User();

        // Populating the user instances
        user1.setId(1L);
        user1.setName("John Fox");
        user1.setPassword("1234");

        user2.setId(2L);
        user2.setName("Jack Ben");
        user2.setPassword("9887");

        // Add each user to the list
        listUsers.add(user1);
        listUsers.add(user2);

        return listUsers;
    }
}
