package com.mangalitsa.litsa.controllers.model;

public record AuthRequest(
        String email,
        String password
) { }
