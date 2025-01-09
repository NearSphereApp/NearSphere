package com.mangalitsa.litsa.controllers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NewUserRequest(
        String email,
        String password,
        @JsonProperty(value = "display_name")
        String displayName
) { }
