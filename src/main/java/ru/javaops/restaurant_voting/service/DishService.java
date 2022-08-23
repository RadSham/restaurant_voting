package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.DishRepository;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.to.DishTo;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class DishService {

    DishRepository dishRepository;
    RestaurantRepository restaurantRepository;

    public Dish createNewFromTo(DishTo dishTo) {
        Restaurant restaurant = restaurantRepository.getById(dishTo.getRestaurantId());
        return new Dish(dishTo.getName(), dishTo.getPrice(), restaurant);
    }

    public List<Dish> convertFromArrayToList(int[] array) {
        List<Dish> dishes = new ArrayList<>();
        for (int i : array) {
            dishes.add(dishRepository.getById(i));
        }
        return dishes;
    }
}
