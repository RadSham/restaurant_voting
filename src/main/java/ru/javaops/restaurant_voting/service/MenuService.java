package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class MenuService {
    MenuRepository menuRepository;
    RestaurantRepository restaurantRepository;

    public Menu save(String name, LocalDate localDate, int restaurantId) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        Menu menu = new Menu(name, localDate, restaurant);
        return menuRepository.save(menu);
    }
}
