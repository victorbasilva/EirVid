/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eirvid;

import java.time.LocalDateTime;
import java.util.Objects;

/*  author: Rafael student number: 2021412 */
public class Rent {
    
    
    public Rent() {}
    
    private Long id;
    private String nome;
    private Movies movie;
    private LocalDateTime date;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rent other = (Rent) obj;
        return Objects.equals(this.id, other.id);
    }

    public Rent(Long id, String nome, Movies movie, LocalDateTime date) {
        this.id = id;
        this.nome = nome;
        this.movie = movie;
        this.date = date;
    }
}
