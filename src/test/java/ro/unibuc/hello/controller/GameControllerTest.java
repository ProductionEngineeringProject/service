package ro.unibuc.hello.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.org.apache.commons.lang.ObjectUtils;
import ro.unibuc.hello.data.InformationEntity;
import ro.unibuc.hello.data.InformationRepository;
import ro.unibuc.hello.dto.GameDTO;
import ro.unibuc.hello.exception.EntityNotFoundException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class GameControllerTest {
//    @Mock
//    private InformationRepository informationRepository;
//    @InjectMocks
//    private GameController gameController;
//
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() throws Exception{
////            informationRepository.deleteAll();
////            informationRepository.save(new InformationEntity("1", "Mario", "I mean..It's Mario", 'E', 14.99d, "Adventure", 87000));
////            informationRepository.save(new InformationEntity("2", "Skyrim", "2011 Best game lol", 'M', 19.99d, "RPG", 196000));
////            informationRepository.save(new InformationEntity("3", "Rust", "Family-Friendly 10/10", 'A', 29.99d, "Survival", 18500));
//
//            mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
//            objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void test_gameDetails() throws Exception{
//        // Arrange
//        InformationEntity entity = new InformationEntity("8", "Lost Ark", "Big MMORPG whoop", 'E', 0.99d, "LifeConsuming", 198765);
//        GameDTO game = new GameDTO(Long.parseLong(entity.getId()), entity.getTitle(), entity.getDescription(), entity.getAgeRating(), entity.getPrice(), entity.getGenre(), entity.getSoldCopies());
//
//        informationRepository.save(entity);
//
//        doReturn(game).when(informationRepository.findById(entity.id));
//
//        // Act &  Assert
//
//        //try{
//            MvcResult result = mockMvc.perform(get("/details?id=8").content(objectMapper.writeValueAsString(game)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//            Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(game));
////        }
////        catch(JsonProcessingException e){
////            System.out.println(e.getMessage());
////        }
////        catch(Exception e) {
////            System.out.println(e.getMessage());
////        }
//    }
//
//    @Test
//    void test_gameDetails_cascadesException() throws Exception{
//
//        // Arrange
//        String id = "1000";
//        when(informationRepository.findById(id)).thenThrow(new EntityNotFoundException(id));
//
//        // Act
//        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, ()->gameController.gameDetails(id), "Expected details to throw an exception, but it did not." );
//
//        // Assert
//        assertTrue(e.getMessage().contains(id));
//    }
//
//    @Test
//    void test_gameList() throws Exception{
//
//        List<InformationEntity> entities = informationRepository.findAll();
//        List<GameDTO> games = new ArrayList<>();
//        for(InformationEntity entity: entities) {
//            GameDTO game = new GameDTO(Long.parseLong(entity.id), entity.title, entity.description, entity.ageRating, entity.price, entity.genre, entity.soldCopies);
//            games.add(game);
//        }
//
//        //try{
//            MvcResult result = mockMvc.perform(get("/catalogue").content(objectMapper.writeValueAsString(games)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//            Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(games));
////        } catch (JsonProcessingException e) {
////            e.printStackTrace();
////        } catch (NullPointerException e) {
////            e.printStackTrace();
////        } catch (UnsupportedEncodingException e) {
////            e.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//    @Test
//    void test_newGameDTO() throws Exception{
//
//        InformationEntity entity = new InformationEntity("8", "Lost Ark", "Big MMORPG whoop", 'E', 0.99d, "LifeConsuming", 198765);
//        GameDTO game = new GameDTO(Long.parseLong(entity.getId()), entity.getTitle(), entity.getDescription(), entity.getAgeRating(), entity.getPrice(), entity.getGenre(), entity.getSoldCopies());
//
//        MvcResult result = mockMvc.perform(post("/new").content(objectMapper.writeValueAsString(entity)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//        Assertions.assertEquals(result.getResponse().getContentAsString(), "Game successfully added!");
//
//        if(informationRepository.findById(entity.getId()).isPresent()){
//            Assertions.assertEquals(entity, informationRepository.findById(entity.getId()).get());
//        }
//        else {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    void test_editSoldCopies() throws Exception{
//
//        InformationEntity entity = new InformationEntity("8", "Lost Ark", "Big MMORPG whoop", 'E', 0.99d, "LifeConsuming", 200);
//
//        informationRepository.save(entity);
//
//        MvcResult result = mockMvc.perform(put("/updateSoldCopies/8?soldCopies=41000").content(objectMapper.writeValueAsString(entity)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//        Assertions.assertEquals(result.getResponse().getContentAsString(), "Sold copies successfully updated !");
//    }

//    @Test
//    void test_editPrice() throws Exception{
//
//        InformationEntity entity = new InformationEntity("8", "Lost Ark", "Big MMORPG whoop", 'E', 0.99d, "LifeConsuming", 198765);
//
//        informationRepository.save(entity);
//
//        MvcResult result = mockMvc.perform(put("/updatePrice/8").content(objectMapper.writeValueAsString(entity)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//        Assertions.assertEquals(result.getResponse().getContentAsString(), "Price successfully updated !");
//    }

}