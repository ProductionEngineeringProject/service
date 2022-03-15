package ro.unibuc.hello.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class InformationEntityTest {

    InformationEntity informationEntity = new InformationEntity("12","Among Us","Fun to play with strangers",'M',3.99d,"Social",45000);

    @Test
    void test_id(){
        Assertions.assertEquals("12",informationEntity.getId());
    }
    @Test
    void test_title(){
        Assertions.assertEquals("Among Us",informationEntity.getTitle());
    }
    @Test
    void test_description(){
        Assertions.assertEquals("Fun to play with strangers",informationEntity.getDescription());
    }
    @Test
    void test_ageRating(){
        Assertions.assertEquals('M',informationEntity.getAgeRating());
    }
    @Test
    void test_price(){
        Assertions.assertEquals(3.99d,informationEntity.getPrice());
    }
    @Test
    void test_genre(){
        Assertions.assertEquals("Social",informationEntity.getGenre());
    }
    @Test
    void test_soldCopies(){
        Assertions.assertEquals(45000,informationEntity.getSoldCopies());
    }
    @Test
    void test_setTitle(){
        informationEntity.setTitle("Mario");
        Assertions.assertEquals("Mario",informationEntity.getTitle());
    }
    @Test
    void test_setDescription(){
        informationEntity.setDescription("New description");
        Assertions.assertEquals("New description",informationEntity.getDescription());
    }
    @Test
    void test_setId(){
        informationEntity.setId("5");
        Assertions.assertEquals("5",informationEntity.getId());
    }
    @Test
    void test_setAge(){
        informationEntity.setAgeRating('E');
        Assertions.assertEquals('E',informationEntity.getAgeRating());
    }
    @Test
    void test_setPrice(){
        informationEntity.setPrice(2.75d);
        Assertions.assertEquals(2.75d,informationEntity.getPrice());
    }
    @Test
    void test_setGenre(){
        informationEntity.setGenre("Horror");
        Assertions.assertEquals("Horror",informationEntity.getGenre());
    }
    @Test
    void test_setCopies(){
        informationEntity.setSoldCopies(5);
        Assertions.assertEquals(5,informationEntity.getSoldCopies());
    }



}