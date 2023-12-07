/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


package eirvid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

        //Movies options and prices source
        
        String fileSelected = "C:\\Users\\victo\\OneDrive\\Documents\\NetBeansProjects\\EirVid\\src\\eirvid\\Resource\\Filmes.csv"; 
        StringBuilder strBuilderResponse = new StringBuilder();

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
    }
    
}
