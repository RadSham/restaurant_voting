package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.error.IllegalRequestDataException;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.to.MenuTo;

import java.util.List;

import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.checkNew;

@Service
@AllArgsConstructor
public class MenuService {

    MenuRepository menuRepository;
    RestaurantRepository restaurantRepository;
    DishService dishService;


    public Menu saveFromTo(MenuTo menuTo) {
        if (menuRepository.getByName(menuTo.getName()) != null)
            throw new IllegalRequestDataException("Menu with name " + menuTo.getName() + " already exist");

        Restaurant restaurant = null;
        if (menuTo.getRestaurantId() != 0)
            restaurant = restaurantRepository.getById(menuTo.getRestaurantId());

        //TODO: connect dish and menu
        /*if (restaurant != null && restaurant.getMenu() != null)
            throw new IllegalRequestDataException("Restaurant '" + restaurant.getName() +
                    "' already has lunch menu '" + restaurant.getMenu().getName() +
                    "'. A restaurant can only have 1 lunch menu. It is also possible to update an existing menu '" +
                    restaurant.getMenu().getName() + "' with id '" + restaurant.getMenu().getId() + "'");
*/
        List<Dish> dishes = null;
        if (menuTo.getDishes() != null)
            dishes = dishService.convertFromArrayToList(menuTo.getDishes());
        Menu menu = new Menu(menuTo.getName(), menuTo.getDate(), restaurant, dishes);
        checkNew(menu);
        System.out.println("MMM " + menu.getId());
        return menuRepository.save(menu);
    }

    public Menu updateFromTo(MenuTo menuTo) {
        if (menuRepository.getByName(menuTo.getName()) != null)
            throw new IllegalRequestDataException("Menu with name " + menuTo.getName() + " already exist");
        Restaurant restaurant = restaurantRepository.getById(menuTo.getRestaurantId());
        List<Dish> dishes = dishService.convertFromArrayToList(menuTo.getDishes());
        return new Menu(menuTo.getName(), menuTo.getDate(), restaurant, dishes);
    }
}
