package ro.unibuc.hello.data;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

public class InformationEntity {

    @Id
    public String id;

    public String title;
    public String description;
    public char ageRating;
    public double price;
    public String genre;
    public int soldCopies;

    public InformationEntity() {}

    public InformationEntity(String id, String title, String description, char ageRating, double price, String genre, int soldCopies) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ageRating = ageRating;
        this.price = price;
        this.genre = genre;
        this.soldCopies = soldCopies;
    }

    @Override
    public String toString() {
        return
                id + '\n' +
                "Title:" + title + '\n' +
                        description + '\n' +
                "Age Rating:" + ageRating + '\n' +
                "Price:" + price + '\n' +
                "Genre:" + genre + '\n' +
                "Sold Copies:" + soldCopies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(char ageRating) {
        this.ageRating = ageRating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getSoldCopies() {
        return soldCopies;
    }

    public void setSoldCopies(int soldCopies) {
        this.soldCopies = soldCopies;
    }
}

