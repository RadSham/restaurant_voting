package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.repository.DishRepository;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.to.DishTo;


@Service
@AllArgsConstructor
public class DishService {

    DishRepository dishRepository;
    MenuRepository menuRepository;

    public Dish saveFromTo(DishTo dishTo) {
        Menu menu = menuRepository.getById(dishTo.getMenuId());
        Dish dish = new Dish(dishTo.getName(),dishTo.getPrice(), menu);
        return dishRepository.save(dish);
    }
}
