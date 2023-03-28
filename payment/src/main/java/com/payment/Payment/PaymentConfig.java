package com.payment.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class PaymentConfig {

    @Bean
    public CommandLineRunner commandLineRunner(PaymentRepository paymentRepository) {
        return args -> {
            if (paymentRepository.count() == 0) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                Payment one = new Payment(
                        "03S91057B30291626",
                        "9PB02794XD215204M",
                        "2B103747NV478603C",
                        "17/03/2023 16:44:03",
                        "captured",
                        "payment captured",
                        null
                );

                Payment two = new Payment(
                        "25E497116L577074N",
                        "3VP2924025800625S",
                        null,
                        "17/03/2023 16:45:10",
                        "cancelled",
                        "payment voided. Seller rejected.",
                        null
                );

                Payment three = new Payment(
                        "42V63370MA871451G",
                        "6FD79275N62012131",
                        "3GK78285YH8079905",
                        "17/03/2023 16:46:20",
                        "captured",
                        "payment captured",
                        null
                );

                Payment four = new Payment(
                        "1TU93321LF9127113",
                        "345027020U373192T",
                        null,
                        "17/03/2023 16:47:23",
                        "pending",
                        "pending seller approval",
                        null
                );

                Payment five = new Payment(
                        "38023272HV8597120",
                        "93A37932RL865251D",
                        "1EU55322S0773643M",
                        "17/03/2023 16:48:28",
                        "captured",
                        "payment captured",
                        null
                );

                Payment six = new Payment(
                        "5GU00943GK5272732",
                        "0K4253282L9678022",
                        "25T69779LA545210R",
                        "17/03/2023 16:52:22",
                        "refunded",
                        "payment refunded.",
                        "15G452082L8338917"
                );

                Payment seven = new Payment(
                        "4LN897407G600393W",
                        "2UN55878FM606840U",
                        null,
                        "17/03/2023 16:53:17",
                        "pending",
                        "pending seller approval",
                        null
                );

                Payment eight = new Payment(
                        "25A98375Y80079222",
                        "96P20704BT6097712",
                        null,
                        "17/03/2023 16:54:37",
                        "cancelled",
                        "payment voided. Seller rejected.",
                        null
                );


                paymentRepository.saveAll(
                        List.of(one, two, three, four, five, six, seven, eight)
                );
            };
        };
    }
}
