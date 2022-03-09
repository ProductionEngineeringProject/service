package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameDTOTest {
    GameDTO game = new GameDTO(7, "Lost Ark", "Kinda addictive", 'E', 2.99, "RPG", 1987000);
    GameDTO game2 = new GameDTO(8, "Lost Ark", "Kinda addictive", 'E', -5.99, "RPG", -7620);

    @Test
    void test_id(){
        Assertions.assertEquals(7, game.getId());
    }

    @Test
    void test_title(){
        Assertions.assertEquals("Lost Ark", game.getTitle());
    }

    @Test
    void test_description(){
        Assertions.assertEquals("Kinda addictive", game.getDescription());
    }

    @Test
    void test_ageRating(){
        Assertions.assertEquals('E', game.getAgeRating());
    }

    @Test
    void test_genre(){
        Assertions.assertEquals("RPG", game.getGenre());
    }

    @Test
    void test_price(){
        Assertions.assertEquals(2.99, game.getPrice());
    }

    @Test
    void test_soldCopies(){
        Assertions.assertEquals(1987000, game.getSoldCopies());
    }

    @Test
    void test_price_ifNegative(){
        Assertions.assertEquals(0, game2.getPrice());
    }

    @Test
    void test_soldCopies_ifNegative(){
        Assertions.assertEquals(0, game2.getSoldCopies());
    }

}