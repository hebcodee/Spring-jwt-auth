package org.example.springauthjwt;

import jakarta.transaction.Transactional;
import org.example.springauthjwt.entities.User;
import org.example.springauthjwt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringAuthJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Transactional
    CommandLineRunner run(UserRepository userRepository){
        return args -> {
            //var userHeb = userRepository.findByEmail("heb@gmail.com");
            var userHeb = userRepository.findByName("heb");

            userHeb.ifPresentOrElse(
                    user -> {
                        System.out.println("User alredy exists");
                    },
                    () -> {
                        var user = new User();
                        user.setName("heb");
                        user.setPassword(passwordEncoder().encode("1234"));
                        userRepository.save(user);
                    }
            );
        };
    }
}
