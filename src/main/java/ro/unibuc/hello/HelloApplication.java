package ro.unibuc.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ro.unibuc.hello.data.InformationEntity;
import ro.unibuc.hello.data.InformationRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = InformationRepository.class)
public class HelloApplication {

	@Autowired
	private InformationRepository informationRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@PostConstruct
	public void runAfterObjectCreated() {
		informationRepository.deleteAll();
		informationRepository.save(new InformationEntity("1", "Mario", "I mean..It's Mario", 'E', 14.99d, "Adventure", 87000));
		informationRepository.save(new InformationEntity("2", "Skyrim", "2011 Best game lol", 'M', 19.99d, "RPG", 196000));
		informationRepository.save(new InformationEntity("3", "Rust", "Family-Friendly 10/10", 'A', 29.99d, "Survival", 18500));
	}

}
