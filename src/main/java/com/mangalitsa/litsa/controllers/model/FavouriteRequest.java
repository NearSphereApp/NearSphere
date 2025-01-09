package com.mangalitsa.litsa.controllers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FavouriteRequest(
        @JsonProperty("place_id")
        String placeId
        // TODO: other fields?
) { }
