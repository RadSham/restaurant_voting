package ru.javaops.restaurant_voting.util;

import lombok.experimental.UtilityClass;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.to.RestaurantTo;

@UtilityClass
public class RestaurantUtil {
    public static Restaurant createNewFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName());
    }
}
