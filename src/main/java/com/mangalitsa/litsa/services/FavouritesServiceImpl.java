package com.mangalitsa.litsa.services;

import com.mangalitsa.litsa.controllers.model.FavouritesRequest;
import com.mangalitsa.litsa.controllers.model.FavouritesResponse;
import com.mangalitsa.litsa.model.Favourites;
import com.mangalitsa.litsa.repositories.FavouritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    private final FavouritesRepository favouritesRepository;

    @Autowired
    public FavouritesServiceImpl(FavouritesRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    public List<FavouritesResponse> getAllFavourites(long userId) {
        return favouritesRepository.findByUserId(userId).stream()
                .map(favourite -> new FavouritesResponse(
                        favourite.getId(),
                        favourite.getDisplayName(),
                        favourite.getPhotoLink(),
                        favourite.getFormattedAddress(),
                        favourite.getWebsite(),
                        favourite.getPriceLevel(),
                        Collections.singletonList(favourite.getTypes())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void addFavourite(long userId, FavouritesRequest favourite) {
        Favourites newFavourite = new Favourites();
        newFavourite.setUserId(favourite.userId());
        newFavourite.setDisplayName(favourite.displayName());
        newFavourite.setPhotoLink(favourite.photoLink());
        newFavourite.setFormattedAddress(favourite.address());
        newFavourite.setPriceLevel(favourite.priceLevel());
        newFavourite.setWebsite(favourite.website());
        newFavourite.setTypes(favourite.types().toString());

        favouritesRepository.save(newFavourite);
    }

    @Override
    public void deleteFavourite(long userId, long placeId) {
        favouritesRepository.deleteByUserIdAndId(userId, placeId);
    }
}