package com.mangalitsa.litsa.controllers.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;



public record FavouritesRequest (
        @JsonProperty(value = "userId")
        long userId,
        @JsonProperty(value = "displayName")
        String displayName,
        @JsonProperty(value = "photoLink")
        String photoLink,
        @JsonProperty(value = "formattedAddress")
        String address,
        @JsonProperty(value = "website ")
        String website,
        @JsonProperty(value = "priceLevel")
        String priceLevel,
        @JsonProperty(value = "types")
        List<String> types
) { }
