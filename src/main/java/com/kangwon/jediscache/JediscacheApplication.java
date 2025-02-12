package com.kangwon.jediscache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JediscacheApplication implements ApplicationRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JediscacheApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(
                User.builder()
                        .name("greg")
                        .email("manuna530@gmail.com")
                        .build()
        );
        userRepository.save(
                User.builder()
                        .name("bob")
                        .email("manuna530@gmail.com")
                        .build()
        );
        userRepository.save(
                User.builder()
                        .name("lion")
                        .email("manuna530@gmail.com")
                        .build()
        );
        userRepository.save(
                User.builder()
                        .name("kim")
                        .email("manuna530@gmail.com")
                        .build()
        );

    }
}
