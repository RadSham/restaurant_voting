package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.error.IllegalRequestDataException;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.to.RestaurantTo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {

    RestaurantRepository restaurantRepository;

    public Restaurant updateFromTo(RestaurantTo restaurantTo, int id) {
        Restaurant updatedRest = checkExistRestaurant(id);
        updatedRest.setName(restaurantTo.getName());
        return updatedRest;
    }

    public Restaurant checkExistRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElseThrow(
                () -> new IllegalRequestDataException("The restaurant ID cannot be 0 or non-existed restaurant. " +
                        "Please enter the ID of an existing restaurant"));
    }

}
