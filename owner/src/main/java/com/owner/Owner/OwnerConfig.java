package com.owner.Owner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OwnerConfig {

    @Bean
    CommandLineRunner commandLineRunner(OwnerRepository ownerRepository){
        return args -> {
            if (ownerRepository.count() == 0) {

                Owner O001 = new Owner(
                        "Alissa Goh",
                        31,
                        12123432,
                        "alissa@gmail.com"
                );

                Owner O002 = new Owner(
                        "Germaine Lim",
                        33,
                        87276883,
                        "gerlim@gmail.com"
                );

                Owner O003 = new Owner(
                        "Brandon Hua",
                        31,
                        12197363,
                        "brandon@gmail.com"
                );

                Owner O004 = new Owner(
                        "Justin Tay",
                        31,
                        96383422,
                        "justin@gmail.com"
                );

                Owner O005 = new Owner(
                        "Tara Jon",
                        40,
                        42905546,
                        "tarajon@gmail.com"
                );

                Owner O006 = new Owner(
                        "Jacob Ma",
                        31,
                        93624713,
                        "jacob@gmail.com"
                );

                Owner O007 = new Owner(
                        "Samantha Low",
                        29,
                        25945597,
                        "samlow@gmail.com"
                );

                Owner O008 = new Owner(
                        "Sheeri Chua",
                        31,
                        12123214,
                        "sheeri@gmail.com"
                );

                Owner O009 = new Owner(
                        "Lisa Monoban",
                        31,
                        12138232,
                        "lisaa@gmail.com"
                );

                ownerRepository.saveAll(
                        List.of(O001, O002, O003, O004, O005, O006, O007, O008, O009)
                );
            };
        };
    }
}
