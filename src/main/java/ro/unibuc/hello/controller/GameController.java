package ro.unibuc.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.data.InformationEntity;
import ro.unibuc.hello.data.InformationRepository;
import ro.unibuc.hello.dto.GameDTO;
import ro.unibuc.hello.dto.Greeting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class GameController {

    @Autowired
    private InformationRepository informationRepository;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/details")
    @ResponseBody
    public GameDTO gameDetails(@RequestParam(name="title", required=false, defaultValue="Skyrim") String title) {
        InformationEntity entity = informationRepository.findByTitle(title);
        return new GameDTO(Long.parseLong(entity.id), entity.title, entity.description, entity.ageRating, entity.price, entity.genre, entity.soldCopies);
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
    public InformationEntity newGameDTO(@RequestBody GameDTO gameDTO) {
        InformationEntity entity = new InformationEntity(String.valueOf(gameDTO.getId()), gameDTO.getTitle(), gameDTO.getDescription(), gameDTO.getAgeRating(), gameDTO.getPrice(), gameDTO.getGenre(), gameDTO.getSoldCopies());
        return informationRepository.save(entity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGame(@PathVariable long id) {
        informationRepository.deleteById(String.valueOf(id));
    }
}
