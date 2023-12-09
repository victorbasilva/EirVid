/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eirvid.Resource;

import eirvid.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class UserLoad {
    
    //Method of loading instances for each user
    
    public List<User> load() {
        
        // Data users list
        
        List<User> listUsers = new ArrayList<>();
    
        //Individual users instances
        
        User user1 = new User();
        User user2 = new User();
//        User user3 = new User();
//        User user4 = new User();
//        User user5 = new User();
//        User user6 = new User();
        
        //Crowding each instance
        
        user1.setId(1L);
        user1.setNome("John");
        user1.setSobreNome("Bart");
        user1.setPassword("1234");
        
        user2.setId(2L);
        user2.setNome("Jack");
        user2.setSobreNome("Done");
        user2.setPassword("9887");
        
       
        //Adding each crowded user in the list that will returned to main class
        
        listUsers.add(user1);
        listUsers.add(user2);

        //list return
        
        return listUsers;
    }
    
}
