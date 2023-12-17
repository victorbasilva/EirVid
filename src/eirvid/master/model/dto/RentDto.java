/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eirvid.master.model.dto;

/**
 *
 * @author Victor
 */
public class RentDto {
    
    public RentDto() {
    }

    private String Id;
    private String nameMovie;
    private String categoryMovie;
    private String yearMovie;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getCategoryMovie() {
        return categoryMovie;
    }

    public void setCategoryMovie(String categoryMovie) {
        this.categoryMovie = categoryMovie;
    }

    public String getYearMovie() {
        return yearMovie;
    }

    public void setYearMovie(String yearMovie) {
        this.yearMovie = yearMovie;
    }

    // Constructor gerado pelo Netbeans
    public RentDto(String id, String nameMovie, String categoryMovie, String yearMovie) {
        this.Id = id;
        this.nameMovie = nameMovie;
        this.categoryMovie = categoryMovie;
        this.yearMovie = yearMovie;
    }
    
}
