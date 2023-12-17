package eirvid.master.model;

import java.util.Objects;

/**
 * Student Name:Joelma Rodrigues
 * Student ID:  2023246
 */


public class UserEirVid {

   public UserEirVid() {
    }

    private Long id;
    private String nameUser;
    private String emailUser;
    private String passwordUser;

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final UserEirVid other = (UserEirVid) obj;
        return Objects.equals(this.id, other.id);
    }

    public UserEirVid(Long id, String nameUser, String emailUser, String passwordUser) {
        this.id = id;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
    }
}
