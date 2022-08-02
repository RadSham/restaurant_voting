package ru.javaops.restaurant_voting.web.restaurant;

import org.hibernate.collection.internal.PersistentList;
import ru.javaops.restaurant_voting.model.*;
import ru.javaops.restaurant_voting.util.JsonUtil;
import ru.javaops.restaurant_voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.*;

import static ru.javaops.restaurant_voting.web.user.UserTestData.admin;

public class AdminTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menus", "votes", "dishes");

    public static final int RESTAURANT_ID_1 = 1;
    public static final int RESTAURANT_ID_5 = 5;
    public static final int NOT_FOUND = 1000;
    static LocalDate CURRENT_DATE = LocalDate.now();

    public static final String ADMIN_MAIL = "admin@gmail.com";

    //TODO: add items to restaurantTest1
    public static Restaurant restaurantTest1 = new Restaurant(RESTAURANT_ID_1, "ALPHA");

    public static List <Dish> dishesTest1 = new ArrayList<Dish>(List.of(new Dish(1, "Grilled Chicken Sandwich", 2.5)));
    public static List<Menu> menuTest1 =  new ArrayList<Menu>(List.of(new Menu(1, "AlphaMenu1", CURRENT_DATE, restaurantTest1, dishesTest1)));
    public static List<Vote> voteTest1 = new ArrayList<Vote>(List.of(new Vote(CURRENT_DATE, admin, restaurantTest1)));

    public static final Restaurant restaurantTest5 = new Restaurant(RESTAURANT_ID_5, "restaurantTest5");
    Collection <Dish> dishesTest5 = new ArrayList<Dish>(Arrays.asList(new Dish(1,"plov", 0.5), new Dish(2, "steak", 2)));
    List<Menu> menuTest5 =  new ArrayList<Menu>(List.of(new Menu(1,"testMenu", CURRENT_DATE, restaurantTest5, dishesTest5)));
    List<Vote> voteTest5 = new ArrayList<Vote>(Arrays.asList(new Vote(CURRENT_DATE, admin, restaurantTest5)));

/*
    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "newPass", false, new Date(), Collections.singleton(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }*/
}
