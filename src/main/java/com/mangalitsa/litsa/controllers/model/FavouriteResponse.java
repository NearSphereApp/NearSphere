package com.mangalitsa.litsa.controllers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FavouriteResponse(
        String id,
        String name,
        @JsonProperty("place_id")
        String placeId
        // TODO
) { }
