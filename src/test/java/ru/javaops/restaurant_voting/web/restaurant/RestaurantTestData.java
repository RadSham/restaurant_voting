package ru.javaops.restaurant_voting.web.restaurant;

import ru.javaops.restaurant_voting.model.*;
import ru.javaops.restaurant_voting.util.JsonUtil;
import ru.javaops.restaurant_voting.web.MatcherFactory;

import java.time.LocalDate;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menus", "votes", "dishes");

    public static final int RESTAURANT_ID_1 = 1;
    public static final int RESTAURANT_ID_2 = 2;
    public static final int RESTAURANT_ID_3 = 3;
    public static final int RESTAURANT_ID_TEST_REST = 4;
    public static final int NOT_FOUND = 1000;
    static LocalDate CURRENT_DATE = LocalDate.now();

    public static final String ADMIN_MAIL = "admin@gmail.com";

    public static Restaurant restaurantTest1 = new Restaurant(RESTAURANT_ID_1, "ALPHA");
    public static Restaurant restaurantTest2 = new Restaurant(RESTAURANT_ID_2, "BETA");
    public static Restaurant restaurantTest3 = new Restaurant(RESTAURANT_ID_3, "GAMMA");

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "testRest");
    }

    public static String jsonRestaurant(Restaurant restaurant) {
        return JsonUtil.writeValue(restaurant);
    }

    public static Restaurant getUpdatedRestaurant() {
        return new Restaurant(RESTAURANT_ID_1, "UpdatedRestaurantName");
    }

}
