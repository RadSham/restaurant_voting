package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.error.IllegalRequestDataException;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.DishRepository;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.to.DishTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class DishService {

    DishRepository dishRepository;
    RestaurantRepository restaurantRepository;

    public Dish createNewFromTo(DishTo dishTo) {
        Restaurant newRestaurant = checkExistRestaurant(dishTo.getRestaurantId());
        return new Dish(dishTo.getName(), dishTo.getPrice(), newRestaurant);
    }

    public List<Dish> convertFromArrayToList(int[] array) {
        List<Dish> dishes = new ArrayList<>();
        for (int i : array) {
            dishes.add(dishRepository.getById(i));
        }
        return dishes;
    }

    public Restaurant checkExistRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElseThrow(
                () -> new IllegalRequestDataException("All dishes must be associated with an existing restaurant. " +
                        "The restaurant ID cannot be 0 or non-existed restaurant. " +
                        "Please enter the ID of an existing restaurant"));

    }
}
