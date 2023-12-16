package eirvid.master.resource;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import eirvid.master.connectdatabase.AccessH2;
import eirvid.master.model.Movie;
import java.io.IOException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadCSVFile {

    List<Movie> movies = new ArrayList<>();
    
    public void readCsv() {

        // Read the Filmes.csv file
        List<String[]> moviesFile = new ArrayList<>();
        Reader reader;
        try {
            reader = Files.newBufferedReader
                (Paths.get("C:\\Users\\victo\\OneDrive\\Documents\\NetBeansProjects\\EirVid\\src\\eirvid\\master\\resource\\MoviesEirVid.csv"));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            moviesFile = csvReader.readAll();
        } catch (IOException ex) {
            Logger.getLogger(ReadCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvException ex) {
            Logger.getLogger(ReadCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Put the data of movies to a list
        Movie movie;
        if (!moviesFile.isEmpty()) {
            for (String[] movieF : moviesFile) {
                movie = new Movie();
                movie.setId(Long.valueOf(movieF[0]));
                movie.setNameMovie(movieF[1]);
                movie.setCategoryMovie(movieF[2]);
                movie.setYearMovie(movieF[3]);
                movie.setPrice(movieF[4]);
                movies.add(movie);
            }
        }
        
        AccessH2 accessH2 = new AccessH2();
        String nameMovie;
        String categoryMovie;
        String yearMovie;
        String price;
        String insertSqlMovie;

        // Save the data in a table of database H2
        for (Movie movieDB : movies) {
            nameMovie = movieDB.getNameMovie();
            categoryMovie = movieDB.getCategoryMovie();
            yearMovie = movieDB.getYearMovie();
            price = movieDB.getPrice();
            insertSqlMovie = "INSERT INTO MOVIE (nameMovie, categoryMovie, yearMovie, price) VALUES (?, ?, ?, ?)";
            accessH2.insertSql(nameMovie, categoryMovie, yearMovie, price, insertSqlMovie);
        }
    }
    
    public List<Movie> listMovies() {
        
        if (!movies.isEmpty()) {
            return movies;
        }
        return null;
    }
}
