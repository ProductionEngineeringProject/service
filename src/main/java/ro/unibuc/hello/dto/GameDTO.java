package ro.unibuc.hello.dto;

import org.springframework.data.annotation.Id;

public class GameDTO {

    private final long id;

    private final String title;
    private final String description;
    private final char ageRating;
    private final double price;
    private final String genre;
    private final int soldCopies;

    public GameDTO(long id, String title, String description, char ageRating, double price, String genre, int soldCopies) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ageRating = ageRating;
        this.price = price;
        this.genre = genre;
        this.soldCopies = soldCopies;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public char getAgeRating() {
        return ageRating;
    }

    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }

    public int getSoldCopies() {
        return soldCopies;
    }
}
