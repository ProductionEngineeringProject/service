package ro.unibuc.hello.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.data.InformationEntity;
import ro.unibuc.hello.data.InformationRepository;
import ro.unibuc.hello.dto.GameDTO;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class GameController {

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    MeterRegistry metricsRegistry;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/details")
    @ResponseBody
    @Timed(value = "game.details.time", description = "Time taken to return a game detail")
    @Counted(value = "game.details.count", description = "Times game details was returned")
    public GameDTO gameDetails(@RequestParam(name="id", required=false, defaultValue="1") String id) {
        metricsRegistry.counter("my_non_aop_metric_details", "endpoint", "details").increment(counter.incrementAndGet());
        Optional<InformationEntity> entity = informationRepository.findById(id);
        return new GameDTO(Long.parseLong(entity.get().id), entity.get().title, entity.get().description, entity.get().ageRating, entity.get().price, entity.get().genre, entity.get().soldCopies);
    }

    @GetMapping("/catalogue")
    @ResponseBody
    @Timed(value = "game.catalogue.time", description = "Time taken to return games catalogue")
    @Counted(value = "game.catalogue.count", description = "Times game catalogue was returned")
    public List<GameDTO> gameList() {
        metricsRegistry.counter("my_non_aop_metric_catalogue", "endpoint", "catalogue").increment(counter.incrementAndGet());
        List<InformationEntity> entities = informationRepository.findAll();
        List<GameDTO> games = new ArrayList<>();
        for(InformationEntity entity: entities) {
            GameDTO game = new GameDTO(Long.parseLong(entity.id), entity.title, entity.description, entity.ageRating, entity.price, entity.genre, entity.soldCopies);
            games.add(game);
        }
        return games;
    }

    @PostMapping("/new")
    @Timed(value = "new.game.time", description = "Time taken to add a new game")
    @Counted(value = "new.game.count", description = "Times a new game was created")
    public ResponseEntity<String> newGameDTO(@RequestBody GameDTO gameDTO) {
        InformationEntity entity = new InformationEntity(String.valueOf(gameDTO.getId()), gameDTO.getTitle(), gameDTO.getDescription(), gameDTO.getAgeRating(), gameDTO.getPrice(), gameDTO.getGenre(), gameDTO.getSoldCopies());

        List<InformationEntity> entities = informationRepository.findAll();

        if(entity.id.equals("0")) {
            Comparator<InformationEntity> comparator = new Comparator<InformationEntity>() {
                @Override
                public int compare(InformationEntity t1, InformationEntity t2) {
                    return Integer.parseInt(t2.getId()) - Integer.parseInt(t1.getId());
                }
            };
            Collections.sort(entities, comparator);

            entity.setId(String.valueOf(Integer.parseInt(entities.get(0).getId()) + 1) );
        }

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

    @PutMapping("/updatePrice/{id}")
    public ResponseEntity<String> editPrice(@PathVariable long id, @RequestBody double price) {

        if(!informationRepository.existsById(String.valueOf(id))){
            return new ResponseEntity<>("Game does not exist !", HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<InformationEntity> ent = informationRepository.findById(String.valueOf(id));

            ent.get().setPrice(price);

            informationRepository.save(ent.get());

            return new ResponseEntity<>("Price successfully updated !", HttpStatus.OK);
        }
    }

    @PutMapping("/updateSoldCopies/{id}")
    public ResponseEntity<String> editSoldCopies(@PathVariable long id, @RequestParam int soldCopies) {

        if(!informationRepository.existsById(String.valueOf(id))){
            return new ResponseEntity<>("Game does not exist !", HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<InformationEntity> ent = informationRepository.findById(String.valueOf(id));

            if(ent.get().getSoldCopies() < soldCopies ){
                ent.get().setSoldCopies(soldCopies);
                informationRepository.save(ent.get());

                return new ResponseEntity<>("Sold copies successfully updated !", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Number of sold copies cannot be less than previous value !", HttpStatus.CONFLICT);
            }
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
