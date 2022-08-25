package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restaurant_voting.error.IllegalRequestDataException;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.to.MenuTo;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuService {

    MenuRepository menuRepository;
    RestaurantRepository restaurantRepository;

    @Transactional
    public Menu saveFromTo(MenuTo menuTo) {
        checkMenuName(menuTo.getName());
        Restaurant restaurant = checkExistRestaurant(menuTo.getRestaurantId());
        checkRestaurantMenu(restaurant);
        Menu menu = new Menu(menuTo.getRestaurantId(), menuTo.getName(), menuTo.getDate(), restaurant);
        //TODO: uncomment
        //checkNew(menu);
        return menuRepository.save(menu);
    }

    public Menu updateFromTo(String  name, int id) {
        checkMenuName(name);
        Menu updatedMenu = checkExistMenu(id);
        updatedMenu.setName(name);
        updatedMenu.setDate(LocalDate.now());
        return updatedMenu;
    }

    public void checkMenuName (String name) {
        if (menuRepository.getByName(name) != null)
            throw new IllegalRequestDataException("Menu with name " + name + " already exist");
    }

    public Restaurant checkExistRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElseThrow(
                () -> new IllegalRequestDataException("All menus must be associated with an existing restaurant. " +
                        "The restaurant ID cannot be 0 or non-existed restaurant. " +
                        "Please enter the ID of an existing restaurant"));
    }

    public Menu checkExistMenu(int id) {
        Optional<Menu> menu = menuRepository.findById(id);
        return menu.orElseThrow(
                () -> new IllegalRequestDataException("Menu with ID " + id + " doesn't exist"));
    }

    public void checkRestaurantMenu(Restaurant restaurant) {
        if (restaurant.getMenu() != null)
            throw new IllegalRequestDataException("Restaurant '" + restaurant.getName() +
                    "' already has lunch menu '" + restaurant.getMenu().getName() +
                    "'. A restaurant can only have 1 lunch menu. It is also possible to update an existing menu '" +
                    restaurant.getMenu().getName() + "' with id '" + restaurant.getMenu().getId() + "'");
    }
}
