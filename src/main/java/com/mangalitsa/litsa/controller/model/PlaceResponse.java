package com.mangalitsa.litsa.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlaceResponse(
        @JsonProperty("google_place_id")
        String googlePlaceId,
        String name
        // TODO: opening_hours
) { }
