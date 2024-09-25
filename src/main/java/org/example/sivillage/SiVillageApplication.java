package org.example.sivillage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing // auditing
@EnableScheduling
public class SiVillageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiVillageApplication.class, args);
    }

}
