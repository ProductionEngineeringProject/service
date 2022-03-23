package ro.unibuc.hello.service;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.InformationEntity;
import ro.unibuc.hello.data.InformationRepository;
import ro.unibuc.hello.dto.GameDTO;
import ro.unibuc.hello.exception.EntityNotFoundException;

@SpringBootTest
public class GameServiceTestIT {

    @Autowired
    public InformationRepository informationRepository;

    @Autowired
    public GameService gameService;

    @Test
    void test_displayGame_returnsGameDTO(){
        // Arrange
        String title = "Mario";

        // Act
        GameDTO game = gameService.displayGame(title);

        // Assert
        Assertions.assertEquals(1L, game.getId());
        Assertions.assertEquals("Mario", game.getTitle());
        Assertions.assertEquals("I mean..It's Mario", game.getDescription());
        Assertions.assertEquals("Adventure", game.getGenre());
        Assertions.assertEquals('E', game.getAgeRating());
        Assertions.assertEquals(14.99d, game.getPrice());
        Assertions.assertEquals(87000, game.getSoldCopies());
    }

    @Test
    void test_displayGame_entityNotFound(){
        // Arrange
        String title = "Brawlhalla";

        // Act & Assert
        Throwable exception = Assertions.assertThrows(EntityNotFoundException.class, ()->gameService.displayGame(title));
        Assertions.assertEquals("Entity: Brawlhalla was not found", exception.getMessage());
    }

    @Test
    void test_editGame_returnsGameDTO(){
        // Arrange
        String title = "Skyrim";
        InformationEntity entity = new InformationEntity("8", "Counter Strike", "I mean..It's CS", 'M', 14.99d, "Shooter", 87000);

        // Act
        GameDTO game = gameService.editGame(entity, title);

        // Assert
        Assertions.assertEquals("Counter Strike", game.getTitle());
        Assertions.assertEquals("I mean..It's CS", game.getDescription());
        Assertions.assertEquals("Shooter", game.getGenre());
        Assertions.assertEquals('M', game.getAgeRating());
        Assertions.assertEquals(14.99d, game.getPrice());
        Assertions.assertEquals(87000, game.getSoldCopies());
    }

    @Test
    void test_editGame_entityNotFound(){
        // Arrange
        String title = "Half Life 3";
        InformationEntity entity = new InformationEntity("8", "Counter Strike", "I mean..It's CS", 'M', 14.99d, "Shooter", 87000);

        // Act & Assert
        Throwable exception = Assertions.assertThrows(EntityNotFoundException.class, ()->gameService.editGame(entity, title));
        Assertions.assertEquals("Entity: Half Life 3 was not found", exception.getMessage());
    }

    @Test
    void test_editPrice_returnsGameDTO(){
        // Arrange
        String title = "Rust";
        double price = 69.99d;

        // Act
        GameDTO game = gameService.editPrice(price, title);

        // Assert
        Assertions.assertEquals(69.99d, game.getPrice());
    }

    @Test
    void test_editNegativePrice_returnsGameDTO(){
        // Arrange
        String title = "Rust";
        double price = -34.25d;

        // Act
        GameDTO game = gameService.editPrice(price, title);

        // Assert
        Assertions.assertEquals(0, game.getPrice());
    }


}
