package com.booking.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class BookingConfig {

    @Bean
    public CommandLineRunner commandLineRunner(BookingRepository bookingRepository) {
        return args -> {
            if (bookingRepository.count() == 0) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                Booking B001 = new Booking(
                        2,
                        5,
                        1,
                        3,
                        LocalDateTime.parse("2022-10-29 12:00:00", formatter),
                        LocalDateTime.parse("2022-11-01 15:00:00", formatter),
                        "confirmed"
                );

                Booking B002 = new Booking(
                        1,
                        2,
                        2,
                        7,
                        LocalDateTime.parse("2022-09-11 09:00:00", formatter),
                        LocalDateTime.parse("2022-09-15 12:00:00", formatter),
                        "pending"
                );

                Booking B003 = new Booking(
                        7,
                        3,
                        6,
                        4,
                        LocalDateTime.parse("2022-11-02 18:00:00", formatter),
                        LocalDateTime.parse("2022-11-05 09:00:00", formatter),
                        "pending"
                );

                Booking B004 = new Booking(
                        6,
                        4,
                        7,
                        5,
                        LocalDateTime.parse("2022-10-29 12:00:00", formatter),
                        LocalDateTime.parse("2022-11-03 09:00:00", formatter),
                        "confirmed"
                );

                Booking B005 = new Booking(
                        3,
                        5,
                        3,
                        8,
                        LocalDateTime.parse("2023-01-10 21:00:00", formatter),
                        LocalDateTime.parse("2023-01-14 15:00:00", formatter),
                        "rejected"
                );

                Booking B006 = new Booking(
                        8,
                        1,
                        5,
                        2,
                        LocalDateTime.parse("2023-03-04 15:00:00", formatter),
                        LocalDateTime.parse("2023-03-07 18:00:00", formatter),
                        "rejected"
                );

                Booking B007 = new Booking(
                        9,
                        2,
                        2,
                        6,
                        LocalDateTime.parse("2023-02-04 09:00:00", formatter),
                        LocalDateTime.parse("2023-02-07 09:00:00", formatter),
                        "cancelled"
                );

                Booking B008 = new Booking(
                        8,
                        8,
                        4,
                        1,
                        LocalDateTime.parse("2023-05-10 18:00:00", formatter),
                        LocalDateTime.parse("2023-05-12 09:00:00", formatter),
                        "confirmed"
                );

                bookingRepository.saveAll(
                        List.of(B001, B002, B003, B004, B005, B006, B007, B008)
                );
            };
        };
    }
}
