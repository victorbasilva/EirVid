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
    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public UserEirVid(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setEmailUser(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNameUser(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPasswordUser(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNameUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getEmailUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPasswordUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
