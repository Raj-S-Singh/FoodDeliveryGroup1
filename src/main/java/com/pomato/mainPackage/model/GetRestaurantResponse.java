package com.pomato.mainPackage.model;

import java.util.Collection;
import java.util.List;

public class GetRestaurantResponse {
    String message;
    List<Restaurant> allRestaurant;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Restaurant> getAllRestaurant() {
        return allRestaurant;
    }

    public void setAllRestaurant(List<Restaurant> allRestaurant) {
        this.allRestaurant = allRestaurant;
    }
}