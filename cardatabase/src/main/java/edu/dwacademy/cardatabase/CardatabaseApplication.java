package edu.dwacademy.cardatabase;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.dwacademy.cardatabase.domain.AppUser;
import edu.dwacademy.cardatabase.domain.AppUserRepository;
import edu.dwacademy.cardatabase.domain.Car;
import edu.dwacademy.cardatabase.domain.CarRepository;
import edu.dwacademy.cardatabase.domain.Owner;
import edu.dwacademy.cardatabase.domain.OwnerRepository;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			CardatabaseApplication.class);

	private final CarRepository repository;
	private final OwnerRepository orepository;
	private final AppUserRepository urepository;
	public CardatabaseApplication(CarRepository repository,
			                      OwnerRepository orepository,
			                      AppUserRepository urepository) {
		this.repository = repository;
		this.orepository = orepository;
		this.urepository = urepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application started");
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner("John" , "Johnson");
	    Owner owner2 = new Owner("Mary" , "Robinson");
	    orepository.saveAll(Arrays.asList(owner1, owner2));
		repository.save(new Car("Ford", "Mustang", "Red",
                "ADF-1121", 2023, 59000,owner1));
      repository.save(new Car("Nissan", "Leaf", "White",
                "SSJ-3002", 2020, 29000,owner2));
      repository.save(new Car("Toyota", "Prius",
                "Silver", "KKO-0212", 2022, 39000,owner2));
      // Fetch all cars and log to console
      for (Car car : repository.findAll()) {
          logger.info("brand: {}, model: {}",
              car.getBrand(), car.getModel());
      }
      
   // Username: user, password: user
      urepository.save(new AppUser("user",
          "$2a$10$wnYMGR9THv9Uo4pCXsKeXuFg0nSdOtHHveEstL/.JyTUeeoRnpdmK","USER"));
      // Username: admin, password: admin
      urepository.save(new AppUser("admin",
          "$2a$10$5toLp5vWfyyJNPJ7CNvJ0ON1nx6vveTMKpQQUzHQlw.WDI5ifBRH2", "ADMIN"));
      
      
		
	}

}
