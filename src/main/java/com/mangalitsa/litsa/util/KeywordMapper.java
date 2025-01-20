package com.mangalitsa.litsa.util;

import java.util.*;

public class KeywordMapper {
    private static final Map<String, List<String>> KEYWORD_TO_TYPES = new HashMap<>();

    static {

        KEYWORD_TO_TYPES.put("culture", Arrays.asList(
                "art_gallery",
                "art_studio",
                "auditorium",
                "cultural_landmark",
                "historical_place",
                "historical_landmark",
                "monument",
                "museum",
                "performing_arts_theater",
                "sculpture",
                "cultural_center"
        ));
        KEYWORD_TO_TYPES.put("entertainment", Arrays.asList(

                "adventure_sports_center",
                "amusement_center",
                "amusement_park",
                "aquarium",
                "banquet_hall",
                "barbecue_area",
                "botanical_garden",
                "bowling_alley",
                "casino",
                "childrens_camp",
                "comedy_club",
                "community_center",
                "concert_hall",
                "convention_center",
                "cycling_park",
                "dance_hall",
                "dog_park",
                "event_venue",
                "ferris_wheel",
                "garden",
                "hiking_area",
                "historical_landmark",
                "internet_cafe",
                "karaoke",
                "marina",
                "movie_rental",
                "movie_theater",
                "national_park",
                "night_club",
                "observation_deck",
                "off_roading_area",
                "opera_house",
                "park",
                "philharmonic_hall",
                "picnic_ground",
                "planetarium",
                "plaza",
                "roller_coaster",
                "skateboard_park",
                "state_park",
                "tourist_attraction",
                "video_arcade",
                "visitor_center",
                "water_park",
                "wedding_venue",
                "wildlife_park",
                "wildlife_refuge",
                "zoo"
        ));

        KEYWORD_TO_TYPES.put("health and wellness", Arrays.asList(

                "massage",
                "sauna",
                "skin_care_clinic",
                "spa",
                "tanning_studio",
                "wellness_center",
                "yoga_studio"
        ));

        KEYWORD_TO_TYPES.put("sports", Arrays.asList(

                "arena",
                "athletic_field",
                "fishing_charter",
                "fitness_center",
                "golf_course",
                "gym",
                "ice_skating_rink",
                "playground",
                "ski_resort",
                "sports_activity_location",
                "sports_club",
                "sports_complex",
                "stadium",
                "swimming_pool"
        ));

        KEYWORD_TO_TYPES.put("nature", Arrays.asList(

                "national_park",
                "park",
                "botanical_garden",
                "wildlife_park",
                "wildlife_refuge",
                "beach",
                "garden",
                "hiking_area"
        ));
        KEYWORD_TO_TYPES.put("religion", Arrays.asList(

                "church",
                "hindu_temple",
                "mosque",
                "synagogue"
        ));
        KEYWORD_TO_TYPES.put("books", Arrays.asList(
                "library",
                "book_store"
        ));

        KEYWORD_TO_TYPES.put("food", Arrays.asList(
                "afghani_restaurant",
                "african_restaurant",
                "american_restaurant",
                "asian_restaurant",
                "bar",
                "bar_and_grill",
                "barbecue_restaurant",
                "brazilian_restaurant",
                "breakfast_restaurant",
                "brunch_restaurant",
                "buffet_restaurant",
                "cafe",
                "cafeteria",
                "candy_store",
                "chinese_restaurant",
                "deli",
                "dessert_restaurant",
                "dessert_shop",
                "diner",
                "fast_food_restaurant",
                "fine_dining_restaurant",
                "food_court",
                "french_restaurant",
                "greek_restaurant",
                "hamburger_restaurant",
                "indian_restaurant",
                "indonesian_restaurant",
                "italian_restaurant",
                "japanese_restaurant",
                "korean_restaurant",
                "lebanese_restaurant",
                "mediterranean_restaurant",
                "mexican_restaurant",
                "middle_eastern_restaurant",
                "pizza_restaurant",
                "pub",
                "ramen_restaurant",
                "restaurant",
                "sandwich_shop",
                "seafood_restaurant",
                "spanish_restaurant",
                "steak_house",
                "sushi_restaurant",
                "tea_house",
                "thai_restaurant",
                "turkish_restaurant",
                "vegan_restaurant",
                "vegetarian_restaurant",
                "vietnamese_restaurant",
                "wine_bar"
        ));
        KEYWORD_TO_TYPES.put("nightlife", Arrays.asList(


                "bar",
                "bar_and_grill",
                "comedy_club",
                "dance_hall",
                "karaoke",
                "night_club",
                "pub",
                "wine_bar"
        ));
    }
    public static List<String> getIncludedTypesForKeyword(String keyword) {
        return KEYWORD_TO_TYPES.getOrDefault(keyword, Collections.emptyList());
    }
}
