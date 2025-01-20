package com.mangalitsa.litsa.controllers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static org.aspectj.apache.bcel.Constants.types;


public record FavouritesRequest (
        @JsonProperty(value = "user_id")
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
