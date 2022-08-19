package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.to.MenuTo;

@Service
@AllArgsConstructor
public class MenuService {
    MenuRepository menuRepository;
    RestaurantRepository restaurantRepository;

    public Menu saveFromTo(MenuTo menuTo) {
        Restaurant restaurant = restaurantRepository.getById(menuTo.getRestaurantId());
        Menu menu = new Menu(menuTo.getName(), menuTo.getDate(), restaurant);
        return menu;
    }
}
