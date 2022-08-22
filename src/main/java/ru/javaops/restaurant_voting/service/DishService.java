package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.repository.DishRepository;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.to.DishTo;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class DishService {

    DishRepository dishRepository;
    MenuRepository menuRepository;

    public Dish createNewFromTo(DishTo dishTo) {
        Menu menu = menuRepository.getById(dishTo.getMenuId());
        Dish dish = new Dish(dishTo.getName(), dishTo.getPrice(), menu);
        return dish;
    }

    public List<Dish> convertFromArrayToList(int[] array) {
        List<Dish> dishes = new ArrayList<>();
        for (int i : array) {
            dishes.add(dishRepository.getById(i));
        }
        return dishes;
    }
}
