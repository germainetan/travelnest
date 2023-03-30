package com.renter.Renter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RenterConfig {

    @Bean
    CommandLineRunner commandLineRunner(RenterRepository renterRepository){
        return args -> {
            if (renterRepository.count() == 0) {
                Renter R001 = new Renter(
                        "John Mann",
                        40,
                        27394836,
                        "johnmann@gmail.com",
                        "It was a pleasure renting to John.",
                        0.9
                );
                Renter R002 = new Renter(
                        "Low Xuanli",
                        21,
                        98242683,
                        "xuanli743@gmail.com",
                        "Xuan Li was an excellent tenant. She kept the property clean and tidy.",
                        0.8
                );
                Renter R003 = new Renter(
                        "Mike T",
                        46,
                        27398732,
                        "mikeeet@gmail.com",
                        "We had a great experience renting to Mike.",
                        0.7
                );
                Renter R004 = new Renter(
                        "Cydnie Na",
                        30,
                        27324236,
                        "cydniena26@gmail.com",
                        "Cydnie was a fantastic tenant. She treated the property like it was her own and was very responsible.",
                        0.9
                );
                Renter R005 = new Renter(
                        "Mark Manson",
                        45,
                        27123657,
                        "markmanson1234@gmail.com",
                        "Mark was a reliable and trustworthy tenant.",
                        0.9);
                Renter R006 = new Renter(
                        "Sarah Ron",
                        24,
                        27353456,
                        "sarahron@gmail.com",
                        "We were very pleased with Sarah as a renter. She took great care of the property.",
                        0.9);
                Renter R007 = new Renter(
                        "David Hanson",
                        48,
                        27392937,
                        "daaavid@gmail.com",
                        "David was a respectful and considerate tenant.",
                        0.9
                );
                Renter R008 = new Renter(
                        "Karen Son",
                        41, 87394836,
                        "karenson@gmail.com",
                        "Karen was a pleasure to work with. She was organized and responsible.",
                        0.9);
                Renter R009 = new Renter(
                        "Fadhli",
                        23, 92343554,
                        "fadhli@gmail.com",
                        "Fadhli was a great tenant who was always courteous and respectful.",
                        1);
                Renter R0010 = new Renter(
                        "Sharon Chua",
                        20, 81394836,
                        "sharoncyt28@gmail.com",
                        "Sharon was an excellent renter and always kept the property clean and well-maintained.",
                        1);

                renterRepository.saveAll(
                        List.of(R001, R002, R003, R004, R005, R006, R007, R008, R009, R0010)
                );
            };
        };
    }
}
