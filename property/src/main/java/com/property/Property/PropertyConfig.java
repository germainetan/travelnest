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
                        4.97,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
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
                        4.89,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
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
                        5,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
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
                        4.78,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
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
                        4.99,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
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
                        5,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
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
                        4.81,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
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
                        4.58,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
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
                        4.75,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
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
                        4.83,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0011 = new Property(
                        "The Boot - Bed and Breakfast",
                        "4",
                        "New Zealand",
                        "Nelson",
                        -41.1956774,
                        173.0573912,
                        "320 Aporo Road, Tasman, Nelson 7152, New Zealand",
                        "1 bedroom, 1 bed",
                        240,
                        2,
                        "Great and peaceful place, Great surroundings for a very special occasion. We felt like in a fairytale in a wonderful, magnificent garden. Most charming place I’ve ever stayed. Ready to move in as soon as the fairy council approves my application. Nice theme and location. Had a great stay.",
                        4.93,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0012 = new Property(
                        "Nacpan Beach Glamping",
                        "2",
                        "Philippines",
                        "Palawan",
                        11.3129685,
                        119.4221952,
                        "Unnamed Road, El Nido, Palawan, Philippines",
                        "1 bedroom, 2 beds",
                        262,
                        2,
                        "Totally worth the money spent. Beautiful stay at Nacpan Beach Glamping. Tent was huge. Wonderful tents, friendly staff, and awesome restaurant next door. We loved the beach glamping at Nacpan for a different experience on holiday. The beach is beautiful and the staff are very friendly. Great stay, highly recommend. We enjoyed our stay at the Glamping spot in Nacpan Beach. The beach is very nice and relaxing. We loved our stay at the Glamping site. The location is very beautiful and the sunsets are dreamy. The included breakfast was very good.",
                        4.77,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0013 = new Property(
                        "Leafy Greens Chiangmai",
                        "6",
                        "Thailand",
                        "Chiang Mai",
                        18.8352768,
                        98.9747151,
                        "71 Chotana Rd, Tambon Chang Phueak, Mueang Chiang Mai District, Chiang Mai 50300, Thailand",
                        "1 bedroom, 2 beds, 1 bathroom",
                        28,
                        2,
                        "Loved our stay here I felt like a fairy. Wonderful earthship houses as well as a naturalistic environment. Amazing place, thank you. Everything was perfect. Love at the first sight for this place. A very special place to stay. It is hard to describe how peaceful and calming this place is. As you come in, you walk through much greenery through paths that lead to your mushroom accommodation. There’s much greenery as others have described, and there even is a stream behind the property where there is a sitting area to relax. We were able to use the washer that was available and it was convenient to be able to hang out our clothes on the available clothes lines.",
                        4.86,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0014 = new Property(
                        "My Castle Khaokho",
                        "5",
                        "Thailand",
                        "Khao Kho",
                        16.6837212,
                        101.0326333,
                        "222 หมู่ 11 Thung Samo, Khao Kho District, Phetchabun 67270, Thailand",
                        "5 bedrooms, 8 beds, 6.5 bathrooms",
                        653,
                        13,
                        "Had an amazing stay at My Castle. The owner was such a sweet and kind gentleman who made us feel so very welcome. Food is delicious with reasonable price. Nice restaurant decoration. Excellent service, nice and friendly staff, magnificent environment, delicious foods and beautiful outdoor accomodation in the tent.",
                        4.4,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0015 = new Property(
                        "The Roundhouses",
                        "1",
                        "Australia",
                        "Tasmania",
                        -43.1392427,
                        147.0339632,
                        "215 Sandhill Rd, Cygnet TAS 7112, Australia",
                        "2 bedrooms, 4 beds, 1 bathroom",
                        195,
                        5,
                        "It was so peaceful looking out at the windy hills, and he lit the fire for us which was so lovely. Little bit of magic . The Round House is all the photos show. Amazing location and space. Very peaceful and so much wildlife in the surrounding area. We had a wonderful stay here. The accommodation was incredible with local wildlife right at our doorstep. The location is off grid, peaceful and quiet. Highly recommend. The views are amazing and even better at sunset looking over the mountains. Would stay here again. Absolutely gorgeous space with unique and creative details. It was such a treat to stay in this beautiful serene space. Will definitely come visit this beautiful private retreat again.",
                        4.89,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0016 = new Property(
                        "Le Mica - Chalets Micro-Element",
                        "6",
                        "Canada",
                        "Québec",
                        46.9777781,
                        -71.2514224,
                        "44 Chemin du Chevreuil, Lac-Beauport, Quebec G3B 0E1, Canada",
                        "2 bedrooms, 4 beds, 1 bathroom",
                        401,
                        4,
                        "Words and pictures cannot describe the beauty and serenity of Le MICA. Perched just below the summit of Mont Tourbillon, the views are breathtaking. The chalet itself is very comfortable and exceptionally clean. Although we felt as though we were miles away from civilization, the chalet is situated amongst biking and snowshoeing/hiking trails, and is a quick drive to Lac Beauport which has an excellent IGA, patisseries, and other conveniences. Quebec City is also very close. Breathtaking vistas and perfectly framed by the open design. The interior spaces provide a 360 viewing experience at all times. Perfectly integrated in the walking and biking trials of Maelstrom peak. It’s a top shelf experience. Incredible stay. Breathtaking views, even better than expected. Enjoyed the proximity to town and the city, but the place itself was the perfect quiet getaway.",
                        4.98,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0017 = new Property(
                        "The Invisible House",
                        "3",
                        "United States",
                        "California",
                        34.1049231,
                        -116.2855981,
                        "8198 Uphill Rd, Joshua Tree, CA 92252, United States",
                        "4 bedrooms, 4 beds, 5 bathrooms",
                        3583,
                        8,
                        "Incredible house. A beautiful home away from home fir our family. The Fieldtrip team was amazing every step of the way and went out of their way to make this beautiful home safe and comfortable for our two-year old. Lived up to all of our expectations. The stay was amazing. Good fun retreat for us. The place itself is surrounded by beautiful landscapes that were just awesome.",
                        4.92,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0018 = new Property(
                        "Alianz Loft",
                        "8",
                        "Costa Rica",
                        "Heredia Province",
                        10.0126391,
                        -84.1372628,
                        "2V67+X3X, Heredia Province, San Roque, Costa Rica",
                        "2 bedrooms, 2 beds, 2 bathrooms",
                        400,
                        4,
                        "This property gave back to the future vibes. Definitely loved it. Personally never wanted to leave. Amazing home. Wish was there longer. All the amenities were fantastic and the place was cozy. We absolutely love this place. Staying in this home is a whole experience.",
                        4.87,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0019 = new Property(
                        "BedrocK Homestead",
                        "7",
                        "United States",
                        "Utah",
                        37.8663612,
                        -111.3574015,
                        "E Burr Trl Rd, Boulder, UT 84716, United States",
                        "3 bedrooms, 4 beds, 2 bathrooms",
                        1462,
                        8,
                        "The house is better then the pictures. Absolutely worth the money. The cave was just as cool as described. The desert holds special energy and this space resonates with it. This is where you come to disconnect from the rest of the world. Bring some extra water. Cherish your time there. Unwind. Go hike. Unplug and appreciate the space.",
                        5,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0020 = new Property(
                        "Casa Acantilado",
                        "9",
                        "Spain",
                        "Granada",
                        36.7441885,
                        -3.6138854,
                        "Urb. el Pargo, 161, 18680 Salobreña, Granada, Spain",
                        "3 bedrooms, 4 beds, 3 bathrooms",
                        854,
                        6,
                        "Great location. Amazing house with a beautiful view. Unique and beautiful property. Fully stocked kitchen. Easy directions and good communication. Absolutely wonderful. Unique architecture and spacious, super clean and views of the ocean and Sunrise to die for. Parking very tight. Lots of stairs inside, not all have railings. Great location for this no less great house. The property is clean and very well equipped.",
                        4.75,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0021 = new Property(
                        "Casa Xochitl",
                        "4",
                        "Mexico",
                        "El Centenario",
                        24.0947319,
                        -110.409403,
                        "C. Miguel Aleman 78, El Centenario, 23205 El Centenario, B.C.S., Mexico",
                        "3 bedrooms, 6 beds, 4.5 bathrooms",
                        549,
                        12,
                        "Beautiful quiet property. Perfect for a weekend getaway. Very nice house on a golf club. The best part of the house is the downstairs outside part with a large green area, pool, huge table, and lounge area. Beautiful house located on the golf course, with an incredible view. The house is very nice. Excellent service. The house is wonderful. Beautiful house and property. Absolutely nothing is needed, it's a wonder of place. Highly recommended. The house exceeded our expectations. We have stayed several times and all have been a pleasure. The space tends to be very hot (nothing that can't be tolerable with the air conditioning), just take that into account. It's paradise in Morelos. The service is incredible, the place is super clean and with very comfortable facilities. The house is a beauty and an architectural jewel. Super service. Excellent host and excellent house. 100% recommended.",
                        4.84,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0022 = new Property(
                        "Villa Imah Caang",
                        "8",
                        "Indonesia",
                        "West Java",
                        -6.8177716,
                        107.5897953,
                        "Jl Kampung Daun Jl. Villa Triniti No.I 39, Cigugur Girang, Parongpong, West Bandung Regency, West Java 40559, Indonesia",
                        "5 bedrooms, 6 beds, 5 bathrooms",
                        353,
                        10,
                        "Nice house with unique design/layout and a well maintained garden. Two rooms at the back of the house had a funky smell but was otherwise clean. Nice house to stay. Better than the picture.",
                        4.93,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0023 = new Property(
                        "BeeHive Homes of Spring",
                        "6",
                        "United States",
                        "Texas",
                        30.0409635,
                        -95.4601719,
                        "3207 Cypresswood Dr, Spring, TX 77388, United States",
                        "1 bedroom, 1 bed, 1 bathroom",
                        376,
                        2,
                        "I love this place. Amazing architecture and beautiful setting. Great place to stay in a really neat neighborhood. Looking forward to booking again on my next trip. Well designed, efficient and unique. Perfect in every way for me. Really cute and excellent space. Fun amenities like the record player, but overall it’s an efficient tiny house with everything you need. Record collection is amazing but is a unique taste. This place is so cool. It is unique architecturally, but inviting, and the outdoor space is nice too. My brother and I stayed here for a weekend, and it was one of the most interesting buildings I’ve ever stayed in.",
                        4.91,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0024 = new Property(
                        "Birdbox Langeland",
                        "2",
                        "Norway",
                        "Skilbrei",
                        61.4031832,
                        5.8003523,
                        "Hafstadstølsvegen 517, 6975 Skilbrei, Norway",
                        "1 bedroom, 1 bed, toilet with sink",
                        536,
                        2,
                        "Amazing camping experience Great location only 19 minutes driving from the nearest town. Try to check the weather before booking cause I wasn’t able to use the grill nor sit outside which would have completed the wonderful experience. This was an incredible experience. Worth every penny. The bed was big and comfy, it was warm inside the box and cozy. There’s about a 200 meter trek to the box and the last bit is pretty steep so try not to bring too much stuff with you when you go. Bit pricey, but definitely worth it for a special stay. It was cosy and comfortable. Amazing view in a great place. Amazing views and relaxation at Birdbox. Would recommend to any couple looking to get away for a one of a kind “glamping” experience in Norway. The views and tranquility here are truly amazing. Would absolutely recommend, such a unique place.",
                        4.88,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0025 = new Property(
                        "輪の家 Ring House",
                        "3",
                        "Japan",
                        "Nagano",
                        36.3039615,
                        138.5560371,
                        "Mozawa, Karuizawa, Kitasaku District, Nagano 389-0114, Japan",
                        "2 bedrooms, 3 beds, 1 bathroom",
                        914,
                        6,
                        "This is a great experience to experience a contemporary Japanese House. We loved the architecture and in November it is a perfect observation point for the Karuizawa autumn foliage. Nice place to stay. Not great at blocking light. It was a beautiful location and a great time. An unforgettable location for a wonderful long weekend getaway. The best was that we could bring our dog.",
                        4.66,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0026 = new Property(
                        "Marina Bay Sands Hotel Deluxe/Premier Room",
                        "9",
                        "Singapore",
                        "Singapore",
                        1.2838755,
                        103.8591172,
                        "10 Bayfront Ave, Singapore 018956",
                        "Studio, 1 bed, 1 shared bathroom",
                        644,
                        4,
                        "We had booked and paid premium rates for a high floor room overlooking the city and water. It was a fabulous room, but after 2 nights we were told that we had to change room to a much lower room, although recently upgraded. I will book ít again soon or later. Excellent customer service with great support. Wonderful hotel. Had such a great time at MBS. Check-in was easy and smooth. Will definitely stay again. It was the most satisfying stay of all the trips. Our overall stay was great. We had such a great view of the gardens from our room. Nice and easy stay. Communication could be faster.",
                        4.67,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0027 = new Property(
                        "StarsBOX",
                        "6",
                        "Italy",
                        "Garessio",
                        44.2042665,
                        8.0155354,
                        "Via Aleramo, 34, 12075 Garessio CN, Italy",
                        "1 bedroom, 1 bed, 1 bathroom",
                        238,
                        2,
                        "We liked everything ,spa very special. It was an experience that exceeded expectations, highly recommended. The most beautiful experience you can have in Italy. Starsbox is a cool, innovative, and super relaxing idea. Falling asleep looking at the stars is a wonder. Very nice place made exceptional for the whirlpool. Not overly immersed in nature, after all there are some condominiums nearby. An evocative and original experience, which I recommend trying. The space is small but very intimate, with nice views of the sea and hill. The private jacuzzi was not comfortable but we relaxed a lot in the other shared tub, which we used almost exclusively on our own. Beautiful experience, magical place, it's an experience to do absolutely. Beautiful experience with a sea view and a romantic atmosphere. We had a great time.",
                        4.68,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0028 = new Property(
                        "Euphoria Art Land",
                        "5",
                        "Cyprus",
                        "Kato Pyrgos",
                        34.7252865,
                        33.1900433,
                        "Vasilikon 76, Pyrgos 4529, Cyprus",
                        "1 bedroom, 1 bed, 1 bathroom",
                        199,
                        2,
                        "An unforgettable stay in an amazingly unique and beautiful place, highly recommend. Great and super unusual place to stay.  It’s more expensive than the average accomodation in Cyprus but I thought it was well worth it. It’s so unique that you can’t miss it.",
                        4.77,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0029 = new Property(
                        "Captain's Rooms",
                        "1",
                        "Greece",
                        "Aegina",
                        37.7439287,
                        23.4326347,
                        "Kapodistriou 19, Egina 180 10, Greece",
                        "1 private bathroom",
                        116,
                        2,
                        "The Captains’ Rooms were amazing. Every bit as quirky as you would expect. Warm and clean and in an incredible location. Probably one of the best private rooms I’ve ever stayed in. The private quarters is on a dock with views of the ocean and mountains on both sides. A very cute cabin with a beautiful view. Small but very confortable. The toilettes et shower are at 20m of the cabine. The host are very attentive. I‘ve spent two nights at the cabin. What a unique, fun and cozy place to stay. Lovely room with an amazing view.",
                        4.57,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                Property P0030 = new Property(
                        "Tree House Munnar",
                        "7",
                        "India",
                        "Kerala",
                        10.010307,
                        77.1074103,
                        "IX/163 Bison Valley, Munnar, Kerala 685565, India",
                        "1 bedroom, 1 bed, 1.5 shared bathrooms",
                        81,
                        2,
                        "The place was over priced, we realised that when we visited a nearby resort for dinner which had much better rooms and amenities at a lower price. The room was not maintained well. The stay was very good especially the view from the tree house and the food served. We really liked the service as the owners were very welcoming and kind to us. I am surely looking forward to stay once again at this property. This is the best tree house I could ever hope for. The location is absolutely amazing. The tree house is a very cosy stay, with stunning views over Munnar. Waking up to the view and bird song felt like a fairytale. The house is perfectly located to explore the area.",
                        4.38,
                        "Car Park, Wi-Fi, Balcony or terrace, Internet, Fans, TV, Hand sanitizer and soap provided, Body wash"
                );

                propertyRepository.saveAll(
                        List.of(P001, P002, P003, P004, P005, P006, P007, P008, P009, P0010, P0011, P0012, P0013, P0014, P0015, P0016, P0017, P0018, P0019, P0020, P0021, P0022, P0023, P0024, P0025, P0026, P0027, P0028, P0029, P0030)
                );
            }
        };
    }
}
