package com.mangalitsa.litsa.services;

import com.mangalitsa.litsa.controllers.model.FavouritesRequest;
import com.mangalitsa.litsa.controllers.model.FavouritesResponse;


import java.util.List;

public interface FavouritesService {
    List<FavouritesResponse> getAllFavourites(long userId);
    void addFavourite(long userId, FavouritesRequest favourite);
    void deleteFavourite(long userId, long placeId);
}