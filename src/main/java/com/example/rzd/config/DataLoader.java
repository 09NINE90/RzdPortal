package com.example.rzd.config;

import com.example.rzd.entity.User;
import com.example.rzd.repository.UserRepository;
import org.apache.commons.jexl3.JxltEngine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    final
    UserRepository userRepository;
    final
    PasswordEncoder encoder;
    public DataLoader(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @java.lang.Override
    public void run(java.lang.String... args) throws JxltEngine.Exception {
        User user = new User();
//        user.setFirstName("Алексей");
//        user.setLastName("Разумов");
//        user.setUserName("raz@ya.ru");
//        user.setPost("Менеджер отдела 1");
//        user.setRoles("ROLE_ADMIN");
//        user.setPhoneNumber("89223434955");
//        user.setPassword(encoder.encode("razumov09!09"));
//        userRepository.save(user);
        System.out.println("Пользователь добавлен!");
    }
}
