package org.XpenseTracks.data.model;

import lombok.Data;

public enum CategoryType {
    GROCERIES("Foodstuff, dining out, snacks, beverages"),
    BILLS("Utilities, rent/mortgage, internet, phone, insurance"),
    ENTERTAINMENT("Movies, concerts, subscriptions (Netflix, Spotify), hobbies"),
    PERSONAL_CARE("Haircuts, skincare, cosmetics, toiletries"),
    TRAVEL("Flights, accommodation, travel insurance, activities"),
    EDUCATION("Tuition, books, supplies, courses"),
    MISCELLANEOUS("Any other expenses that don't fit into the above categories");


    private final String description;
    CategoryType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
