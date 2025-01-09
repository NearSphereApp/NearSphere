package com.mangalitsa.litsa.controllers;

import com.mangalitsa.litsa.controllers.model.PlaceResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/place")
public class PlaceController {

    @GetMapping
    public List<PlaceResponse> getPlaces(
            // e.g. ?location=156,156&radius=5000&keyword=hello&keyword=world
            // location = "156,156"
            // radius = 5000
            // keywords = { "hello", "world" }
            @RequestParam String location,
            @RequestParam int radius,
            @Nullable
            @RequestParam(name = "keyword", required = false)
            List<String> keywords
    ) {
        // TODO: defer to service class
        return List.of(new PlaceResponse("some_id", "a name"));
    }

}
