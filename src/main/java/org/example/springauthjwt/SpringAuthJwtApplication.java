package org.example.springauthjwt;

import org.example.springauthjwt.entities.User;
import org.example.springauthjwt.service.UserService;
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
    CommandLineRunner run(UserService userService){
        return args -> {
          userService.saveUser(new User(null, "Herberth Guimaraes", "heb@gmail.com", "1234"));
          userService.saveUser(new User(null, "Will Smith", "will@gmail.com", "1234"));
          userService.saveUser(new User(null, "Jim Carry", "jin@gmail.com", "1234"));
          userService.saveUser(new User(null, "Willian Bonner", "bonner@gmail.com", "1234"));


        };
    }
}
