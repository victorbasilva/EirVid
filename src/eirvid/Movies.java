
import java.math.BigDecimal;
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alessandracaballero
 * student number: 2021258
 */
public class Movies {
    public Movies() {}

    //Variables from each columm on the .csv file
    
    private Integer Id;
    private String name;
    private String category;
    private String year;
    private BigDecimal price;

    // Getters and Setters
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //Hash code
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    //Equals
    
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
        final Movies other = (Movies) obj;
        return Objects.equals(this.Id, other.Id);
    }

    //ToString
    
    @Override
    public String toString() {
        return "Movies{" + "Id=" + Id + ", name=" + name + ", category=" + category + ", year=" + year + ", price=" + price + '}';
    }

    //Constructor
    
    public Movies(Integer Id, String name, String category, String year, BigDecimal price) {
        this.Id = Id;
        this.name = name;
        this.category = category;
        this.year = year;
        this.price = price;
    }
    
}
