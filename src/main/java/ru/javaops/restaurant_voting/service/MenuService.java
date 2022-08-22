package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.error.IllegalRequestDataException;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.DishRepository;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.to.MenuTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {
    MenuRepository menuRepository;
    RestaurantRepository restaurantRepository;
    DishService dishService;

    public Menu saveFromTo(MenuTo menuTo) {
        if (menuRepository.getByName(menuTo.getName()) != null)
            throw new IllegalRequestDataException("Menu with name " + menuTo.getName() + " already exist");
        else if (restaurantRepository.getById(menuTo.getRestaurantId()).getMenu() != null)
            throw new IllegalRequestDataException("Restaurant '" + restaurantRepository.getById(menuTo.getRestaurantId()).getName() +
                    "' already has lunch menu '" + menuTo.getName() +
                    "'. A restaurant can only have 1 lunch menu. It is also possible to update an existing menu '" +
                    restaurantRepository.getById(menuTo.getRestaurantId()).getMenu().getName() +
                    "' with id '" + restaurantRepository.getById(menuTo.getRestaurantId()).getMenu().getId() + "'");
        Restaurant restaurant = restaurantRepository.getById(menuTo.getRestaurantId());
        List<Dish> dishes = dishService.convertFromArrayToList(menuTo.getDishes());
        return new Menu(menuTo.getName(), menuTo.getDate(), restaurant, dishes);
    }
}
