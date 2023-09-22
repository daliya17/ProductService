package dev.daliya.productService;

import dev.daliya.productService.inheritancedemo.tableperclass.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private MentorRepository mentorRepository;
    private UserRepository userRepository;

    private TARepository taRepository;

    public ProductServiceApplication(@Qualifier("tpc_mr") MentorRepository mentorRepository,
                                     @Qualifier("tpc_ur") UserRepository userRepository,
                                     @Qualifier("tpc_ta") TARepository taRepository) {
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.taRepository = taRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        TA mentor = new TA();
        mentor.setName("Daliya");
        mentor.setEmail("daliyajohnson33@gmail.com");
        mentor.setAverageRating(90);
        System.out.println(mentor);
        taRepository.save(mentor);

//		User user = new User();
//		user.setName("Dalia");
//		user.setEmail("daliyajohnson33@gmail.com");
//		userRepository.save(user);
    }
}
