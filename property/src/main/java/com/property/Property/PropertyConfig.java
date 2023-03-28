package com.property.Property;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PropertyConfig {

    @Bean
    CommandLineRunner commandLineRunner(PropertyRepository propertyRepository){
        return args -> {
            if (propertyRepository.count() == 0) {

                Property P001 = new Property(
                        "Turtle Bay HuaHin eco luxeTurt Villa in Lotus Bay",
                        "5",
                        "Thailand",
                        "Hua Hin District",
                        12.4544743,
                        99.97683986,
                        "77, หมู่บ้าน, 3 Hua-Hin 101, Nong Kae, Hua Hin District, Prachuap Khiri Khan 77110, Thailand",
                        "2 double beds",
                        147,
                        1,
                        "The staff was so attentive and helpful, such a cultivated experience. Very peaceful environment, attention to detail was exquisite.",
                        4.97
                );

                Property P002 = new Property(
                        "Hideout Horizon - Eco Bamboo Home",
                        "2",
                        "Indonesia",
                        "Bali",
                        -8.450139321,
                        115.4834987,
                        "Jl. Jangu, Duda, Kec. Selat, Kabupaten Karangasem, Bali 80862, Indonesia",
                        "1 double bed, 2 single beds",
                        386,
                        4,
                        "Loved the stay. Beautiful peaceful place for relaxing and honeymoon couples. Perfection. A stunning building full of thoughtful details. We wanted to experience Bali's unique bamboo architecture and Hideout did not disappoint.",
                        4.89
                );

                Property P003 = new Property(
                        "Gawthorne's Hut",
                        "5",
                        "Australia",
                        "Buckaroo",
                        -32.5785253,
                        149.6351188,
                        "240 Edgell Ln, Buckaroo NSW 2850, Australia",
                        "1 king bed",
                        446,
                        2,
                        "The hut was beautifully designed and we loved that it was off grid. The fresh coffee, chocolate, bottle of wine and a decent amount of milk was a nice touch when we arrived. There was no shortage of natural light. Super friendly and very responsive. We had a fabulous stay here. Very cozy and relaxing surroundings made us unwind. Gawthorne’s is a lovely place to stay, would definitely recommend.",
                        5
                );

                Property P004 = new Property(
                        "Converted Bus near Universiti Malaya",
                        "8",
                        "Malaysia",
                        "Kuala Lumpur",
                        3.120821289,
                        101.6481205,
                        "2, Lorong 16/10b, Seksyen 16, 46350 Petaling Jaya, Wilayah Persekutuan Kuala Lumpur, Malaysia",
                        "1 queen bed",
                        48,
                        4,
                        "I stayed just one night and I found 10 mosquito bites on my leg in the morning. Unique and awesome concept staying in a refurbished bus. Impressed at the interior decor including airconditioning and a sink and enough power points to charge gadgets. The bas is nice and clean, but the tiole far not so convenience if you want go tiolet at night.",
                        4.78
                );

                Property P005 = new Property(
                        "Le Manoir des Bougainvilliers",
                        "1",
                        "Philippines",
                        "Puerto Galera",
                        13.49228771,
                        120.9558244,
                        "628 Baclayan Lane, up hill Small, Puerto Galera, 5203 Oriental Mindoro, Philippines",
                        "8 bedrooms, 12 beds",
                        49,
                        16,
                        "Definitely will come again. Wonderful host. Beautiful place. Great cooks. Centrally located. Recommended for a family /friend get away. The house is beautiful - we were mind-blown by the design and the lovely terraces that we could use in particular. Come as a guest and leave as family. Absolutely the best host hands down. We had a wonderful time at this \"magical\" place. So much effort must have gone into designing every tiny little bit of it. Highly recommended. The best experience. We all loved hearing your adventures from all over the world, especially my daughter. Thanks again for this experience and we will surely come back for more. The food is absolutely amazing – everything we tasted melted in other mouths. Absolutely the best meal we had.",
                        4.99
                );

                Property P006 = new Property(
                        "Villa The Cloud",
                        "3",
                        "Indonesia",
                        "Bali",
                        -8.563399175,
                        115.0386541,
                        "Tibubiu, Kerambitan, Tabanan Regency, Bali, Indonesia",
                        "3 king beds, 1 sofa, 1 cot",
                        4067,
                        7,
                        "Very relaxing. The swimming pool is awesome and all the facilities are great. Private and spacious futuristic villa with fantastic swimming pool, and lovely out door area. Wonderful location. Everything was perfect - location, service, vibe, etc. Fabulous pool and veeery beautiful design. It was peaceful and we relaxed easily.",
                        5
                );

                Property P007 = new Property(
                        "Kellogg House",
                        "4",
                        "United States",
                        "California",
                        34.12100674,
                        -116.2244564,
                        "7263 Mt Shasta Ave, Joshua Tree, CA 92252, USA",
                        "1 king bed, 2 queen beds",
                        6973,
                        6,
                        "This is the most amazing place in the world. Every detail of this house can be called a work of art.",
                        4.81
                );

                Property P008 = new Property(
                        "Amsterdam The Crane by YAYS",
                        "6",
                        "Netherlands",
                        "Amsterdam",
                        52.37754698,
                        4.939462182,
                        "Surinamekade 34, 1019 BH Amsterdam, Netherlands",
                        "1 double bed",
                        688,
                        2,
                        "Totally novel and with the most amazing views. Info on the history of the crane was excellent. Creaks, clangs, bangs etc. bring ear plugs. Truly unique and unforgettable. It's a very special place, well situated and the place is convenient.",
                        4.58
                );

                Property P009 = new Property(
                        "Heavot Caves Resort",
                        "9",
                        "India",
                        "Himachal Pradesh",
                        31.16097481,
                        77.1484634,
                        "Village dummi, Shimla, Himachal Pradesh 171003, India",
                        "1 king bed, 1 floor mattress",
                        58,
                        3,
                        "Great place if you want to have a peaceful getaway. Nice place to visit. This place was unique and an escape from the daily hustle. Check in and other services were great.",
                        4.75
                );

                Property P0010 = new Property(
                        "Buff and Fellow",
                        "7",
                        "South Africa",
                        "George",
                        -34.01944485,
                        22.29782688,
                        "226 Diepekloof, Ultreya Farm Grootbrak, George, 6535, South Africa",
                        "2 king beds, 1 sofa bed",
                        220,
                        6,
                        "Beautiful farm, Super cool concept, amazing to see the wildlife. This place was so unique and beautiful, we all loved the experience and beautiful design of our Eco cocoon we stayed at. Very unique and peaceful place. The animals were a beautiful site. Nice setup with well designed cabins and awesome animals around (even rhinos!). Awesome,unique stay. Time to relax in your own Hey Day setting. This is a great experience for overseas travellers, as you get to see some wild life. Loved our stay entirely. We liked the view on animals. We saw buffaloes, rhinoceros, antelopes. The style of the house is nice and there is a really big hot tube. I spent my birthday here and what can I say, “Magical” doesn’t do enough justice. Fantastic in every way. Will be tough to get a booking in future because I'm pretty sure a gem like this will be wildly popular. We will definitely return.",
                        4.83
                );

                propertyRepository.saveAll(
                        List.of(P001, P002, P003, P004, P005, P006, P007, P008, P009, P0010)
                );
            };
        };
    }
}
