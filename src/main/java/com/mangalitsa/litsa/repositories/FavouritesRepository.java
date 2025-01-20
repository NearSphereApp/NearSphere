package com.mangalitsa.litsa.repositories;


import com.mangalitsa.litsa.model.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
    List<Favourites> findByUserId(long userId);
    void deleteByUserIdAndId(long userId, long Id);
}
