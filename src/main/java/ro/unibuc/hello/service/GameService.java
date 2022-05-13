package ro.unibuc.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.unibuc.hello.data.InformationEntity;
import ro.unibuc.hello.data.InformationRepository;
import ro.unibuc.hello.dto.GameDTO;
import ro.unibuc.hello.exception.EntityNotFoundException;

@Component
public class GameService {

    @Autowired
    public InformationRepository informationRepository;

    public GameDTO displayGame(String title) throws EntityNotFoundException {

        InformationEntity entity = informationRepository.findByTitle(title);

        if(entity == null){
            throw new EntityNotFoundException(title);
        }
            GameDTO game = new GameDTO(Long.parseLong(entity.id), entity.title, entity.description, entity.ageRating, entity.price, entity.genre, entity.soldCopies);
            return game;
    }

    public GameDTO editGame(InformationEntity aux, String title) throws EntityNotFoundException{

        InformationEntity entity = informationRepository.findByTitle(title);

        if(entity == null){
            throw new EntityNotFoundException(title);
        }
        entity.setTitle(aux.title);
        entity.setDescription(aux.description);
        entity.setAgeRating(aux.ageRating);
        entity.setPrice(aux.price);
        entity.setGenre(aux.genre);
        entity.setSoldCopies(aux.soldCopies);

        //informationRepository.save(entity);
        //entity = informationRepository.findByTitle(title);

        GameDTO game = new GameDTO(Long.parseLong(entity.id), entity.title, entity.description, entity.ageRating, entity.price, entity.genre, entity.soldCopies);
        return game;
    }

    public GameDTO editPrice(double price, String title) throws EntityNotFoundException{

        InformationEntity entity = informationRepository.findByTitle(title);

        if(entity == null){
            throw new EntityNotFoundException(title);
        }

        entity.setPrice(price);

        informationRepository.save(entity);

        GameDTO game = new GameDTO(Long.parseLong(entity.id), entity.title, entity.description, entity.ageRating, entity.price, entity.genre, entity.soldCopies);
        return game;
    }

}
