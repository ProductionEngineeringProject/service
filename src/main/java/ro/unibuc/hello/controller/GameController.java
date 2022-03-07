package ro.unibuc.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.data.InformationEntity;
import ro.unibuc.hello.data.InformationRepository;
import ro.unibuc.hello.dto.GameDTO;
import ro.unibuc.hello.dto.Greeting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class GameController {

    @Autowired
    private InformationRepository informationRepository;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/details")
    @ResponseBody
    public GameDTO gameDetails(@RequestParam(name="id", required=false, defaultValue="1") String id) {
        Optional<InformationEntity> entity = informationRepository.findById(id);
        return new GameDTO(Long.parseLong(entity.get().id), entity.get().title, entity.get().description, entity.get().ageRating, entity.get().price, entity.get().genre, entity.get().soldCopies);
    }

    @GetMapping("/catalogue")
    @ResponseBody
    public List<GameDTO> gameList() {
        List<InformationEntity> entities = informationRepository.findAll();
        List<GameDTO> games = new ArrayList<>();
        for(InformationEntity entity: entities) {
            GameDTO game = new GameDTO(Long.parseLong(entity.id), entity.title, entity.description, entity.ageRating, entity.price, entity.genre, entity.soldCopies);
            games.add(game);
        }
        return games;
    }

    @PostMapping("/new")
    public ResponseEntity<String> newGameDTO(@RequestBody GameDTO gameDTO) {
        InformationEntity entity = new InformationEntity(String.valueOf(gameDTO.getId()), gameDTO.getTitle(), gameDTO.getDescription(), gameDTO.getAgeRating(), gameDTO.getPrice(), gameDTO.getGenre(), gameDTO.getSoldCopies());

        List<InformationEntity> entities = informationRepository.findAll();
        boolean found = false;
        for(InformationEntity ent : entities) {
            if(ent.id.equals(entity.id) || Integer.parseInt(entity.id) < 0) {
                found = true;
                break;
            }
        }
        if(found == false){
            informationRepository.save(entity);
            return new ResponseEntity<>("Game successfully added!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("ID already exists / ID is negative !", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> editGame(@PathVariable long id, @RequestBody GameDTO gameDTO) {

        InformationEntity entity = new InformationEntity(String.valueOf(gameDTO.getId()), gameDTO.getTitle(), gameDTO.getDescription(), gameDTO.getAgeRating(), gameDTO.getPrice(), gameDTO.getGenre(), gameDTO.getSoldCopies());

        if(!informationRepository.existsById(String.valueOf(id))){
            return new ResponseEntity<>("Game does not exist !", HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<InformationEntity> ent = informationRepository.findById(String.valueOf(id));

            ent.get().setTitle(entity.title);
            ent.get().setDescription(entity.description);
            ent.get().setAgeRating(entity.ageRating);
            ent.get().setPrice(entity.price);
            ent.get().setGenre(entity.genre);
            ent.get().setSoldCopies(entity.soldCopies);

            informationRepository.save(ent.get());

            return new ResponseEntity<>("Game successfully updated !", HttpStatus.OK);
        }
    }

    @PutMapping("/updateState/{id}")
    public ResponseEntity<String> editPriceAndSoldCopies(@PathVariable long id, @RequestBody GameDTO gameDTO) {

        InformationEntity entity = new InformationEntity(String.valueOf(gameDTO.getId()), gameDTO.getTitle(), gameDTO.getDescription(), gameDTO.getAgeRating(), gameDTO.getPrice(), gameDTO.getGenre(), gameDTO.getSoldCopies());

        if(!informationRepository.existsById(String.valueOf(id))){
            return new ResponseEntity<>("Game does not exist !", HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<InformationEntity> ent = informationRepository.findById(String.valueOf(id));

            ent.get().setPrice(entity.price);
            ent.get().setSoldCopies(entity.soldCopies);

            informationRepository.save(ent.get());

            return new ResponseEntity<>("Price and sold copies successfully updated !", HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable long id) {

        List<InformationEntity> entities = informationRepository.findAll();

        boolean found = false;
        for(InformationEntity ent : entities) {
            if(ent.id.equals(String.valueOf(id))) {
                found = true;
                break;
            }
        }
        if(found == false) {
            return new ResponseEntity<>("This ID does not exist !", HttpStatus.BAD_REQUEST);
        }
        else {
            informationRepository.deleteById(String.valueOf(id));
            return new ResponseEntity<>("Game successfully deleted !", HttpStatus.OK);
        }
    }
}
