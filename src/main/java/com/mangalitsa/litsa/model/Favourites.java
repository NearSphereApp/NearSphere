package com.mangalitsa.litsa.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_favourite_places")
public class Favourites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;


    @Column(name = "display_name")
    private String displayName;

    @Column(name = "photo_link")
    private String photoLink;

    @Column(name = "formatted_address")
    private String formattedAddress;

    @Column(name = "website")
    private String website;

    @Column(name = "price_level")
    private String priceLevel;

    @Column(name = "types")
    private String types;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    public Favourites() {}

    public Favourites(Long userId, String displayName, String photoLink, String formattedAddress, String website, String priceLevel, String types) {
        this.userId = userId;
        this.displayName = displayName;
        this.photoLink = photoLink;
        this.formattedAddress = formattedAddress;
        this.website = website;
        this.priceLevel = priceLevel;
        this.types = types;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getPhotoLink() { return photoLink; }
    public void setPhotoLink(String photoLink) { this.photoLink = photoLink; }
    public String getFormattedAddress() { return formattedAddress; }
    public void setFormattedAddress(String formattedAddress) { this.formattedAddress = formattedAddress; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public String getPriceLevel() { return priceLevel; }
    public void setPriceLevel(String priceLevel) { this.priceLevel = priceLevel; }
    public String getTypes() { return types; }
    public void setTypes(String types) { this.types = types; }
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}