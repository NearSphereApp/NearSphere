package com.mangalitsa.litsa.controllers;

import com.mangalitsa.litsa.controllers.model.FavouriteRequest;
import com.mangalitsa.litsa.controllers.model.FavouriteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Collections.emptyList;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouritesController {

    @GetMapping
    public List<FavouriteResponse> getFavourites() {
        return emptyList(); //TODO
    }

    @GetMapping("/{id}")
    public FavouriteResponse getFavourite(@PathVariable String id) {
        return null; //TODO
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FavouriteResponse addFavourite(@RequestBody FavouriteRequest body) {
        return null; //TODO
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavourite(@PathVariable String id) {
        // TODO
    }

}
