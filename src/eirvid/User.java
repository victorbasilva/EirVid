package eirvid;

import java.util.Objects;

/**
 * Student Name:Joelma Rodrigues
 * Student ID:  2023246
 */

public class User {
    long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    // Constructors
    public User(Long id, String name, String surname, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //override toString method
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    //override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { //if the object is the same
            return true;
        }
        if (obj == null) {//if the object is null
            return false;
        }
        if (getClass() != obj.getClass()) {// if the object is not the same class
            return false;
        }

        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }
}
