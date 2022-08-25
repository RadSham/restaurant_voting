package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.error.IllegalRequestDataException;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.DishRepository;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.to.DishTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@AllArgsConstructor
public class DishService {

    DishRepository dishRepository;
    RestaurantRepository restaurantRepository;
    MenuRepository menuRepository;

    public Dish createNewFromTo(DishTo dishTo) {
        Restaurant newRestaurant = checkExistRestaurant(dishTo.getRestaurantId());
        Menu newMenu = checkExistMenu(dishTo.getMenuId());
        return new Dish(dishTo.getName(), dishTo.getPrice(), newRestaurant, newMenu);
    }

    public Dish updateFromTo(DishTo dishTo) {
        Restaurant updRestaurant = checkExistRestaurant(dishTo.getRestaurantId());
        Menu updMenu = checkExistMenu(dishTo.getMenuId());
        compareMenuWithRestaurant(updMenu, updRestaurant);
        return new Dish(dishTo.getName(), dishTo.getPrice(), updRestaurant, updMenu);
    }


    public Restaurant checkExistRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElseThrow(
                () -> new IllegalRequestDataException("All dishes must be associated with an existing restaurant. " +
                        "The restaurant ID cannot be 0 or non-existed restaurant. " +
                        "Please enter the ID of an existing restaurant"));

    }

    public Menu checkExistMenu(int id) {
        if (id == 0) return null;
        Optional<Menu> menu = menuRepository.findById(id);
        return menu.orElseThrow(
                () -> new IllegalRequestDataException("Menu with ID " + id + " doesn't exist"));
    }

    //TODO: to finish
    public void compareMenuWithRestaurant(Menu menu, Restaurant restaurant) {
        if (menu==null) return;
        try {
            if (!Objects.equals(menu.getId(), restaurant.getId())) {
                throw new IllegalRequestDataException("Menu can't be different with restaurantID, that's why we set menu.id as" + restaurant.getId());
            }
        } catch (IllegalRequestDataException e) {
            System.out.println(e.getMessage());
            menu.setId(restaurant.getId());
        }
    }
}
