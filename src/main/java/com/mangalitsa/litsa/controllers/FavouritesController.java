package com.mangalitsa.litsa.controllers;

import com.mangalitsa.litsa.controllers.model.FavouritesRequest;
import com.mangalitsa.litsa.controllers.model.FavouritesResponse;
import com.mangalitsa.litsa.services.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/favourites")
public class FavouritesController {

    private final FavouritesService favouritesService;

    @Autowired
    public FavouritesController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<FavouritesResponse>> getFavourites(@PathVariable long user_id) {
        List<FavouritesResponse> favourites = favouritesService.getAllFavourites(user_id);
        if (favourites.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favourites);
    }


    @PostMapping("/{user_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus addFavourite(@PathVariable long user_id, @RequestBody FavouritesRequest body) {
        favouritesService.addFavourite(user_id, body);
        return HttpStatus.CREATED;

    }

    @DeleteMapping("/{user_id}/{place_id}")
    public HttpStatus deleteFavourite(@PathVariable long user_id, @PathVariable long place_id) {
        favouritesService.deleteFavourite(user_id, place_id);
        return HttpStatus.NO_CONTENT;
    }

}
