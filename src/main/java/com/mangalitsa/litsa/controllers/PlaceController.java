package com.mangalitsa.litsa.controllers;

import com.mangalitsa.litsa.controllers.model.PlacesRequest;
import com.mangalitsa.litsa.controllers.model.PlacesResponse;
import com.mangalitsa.litsa.services.PlaceService;
import com.mangalitsa.litsa.util.KeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public List<PlacesResponse> getNearbyPlaces(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius,
            @RequestParam(required = false) List<String> keywords) {

        Set<PlacesResponse> aggregatedResponses = new HashSet<>();

        if (keywords != null) {
            for (String keyword : keywords) {
                List<String> includedTypes = KeywordMapper.getIncludedTypesForKeyword(keyword);

                PlacesRequest requestDto = new PlacesRequest(
                        latitude,
                        longitude,
                        radius,
                        new ArrayList<>(includedTypes)
                );

                List<PlacesResponse> keywordResponses = placeService.getNearbyPlaces(requestDto);
                aggregatedResponses.addAll(keywordResponses);
            }
        }

        return new ArrayList<>(aggregatedResponses);
    }
}
