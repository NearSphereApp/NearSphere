package com.mangalitsa.litsa.controllers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        long id,
        String email,
        @JsonProperty("display_name")
        String displayName
) { }
