package ru.javaops.restaurant_voting.web.vote;

import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.model.Role;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.web.MatcherFactory;

import java.time.LocalDate;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingEqualsComparator(Vote.class);

    public static final int VOTE_ID_1 = 1;
    public static final int VOTE_ID_2 = 1;

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int USER2_ID = 3;

    public static final String USER_MAIL = "user@yandex.ru";
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String USER2_MAIL = "user2@yandex.ru";

    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "User", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);
    public static final User user2 = new User(USER2_ID, "Use2r", USER2_MAIL, "password2", Role.USER);

    public static final int RESTAURANT_ID_1 = 1;
    public static final int RESTAURANT_ID_2 = 2;
    public static final int RESTAURANT_ID_3 = 3;

    public static Restaurant restaurantTest1 = new Restaurant(RESTAURANT_ID_1, "ALPHA");
    public static Restaurant restaurantTest2 = new Restaurant(RESTAURANT_ID_2, "BETA");
    public static Restaurant restaurantTest3 = new Restaurant(RESTAURANT_ID_3, "GAMMA");

    public static Vote vote1User = new Vote(1, LocalDate.now().minusDays(3), user, restaurantTest3);
    public static Vote vote2Admin = new Vote(2, LocalDate.now().minusDays(3), admin, restaurantTest2);
    public static Vote vote3User2 = new Vote(3,LocalDate.now().minusDays(3), user2, restaurantTest1);
    public static Vote vote4User = new Vote(4, LocalDate.now().minusDays(2), user, restaurantTest2);
    public static Vote vote5Admin = new Vote(5, LocalDate.now().minusDays(2), admin, restaurantTest1);
    public static Vote vote6User2 = new Vote(6,LocalDate.now().minusDays(2), user2, restaurantTest3);
    public static Vote vote7User = new Vote(7, LocalDate.now().minusDays(1), user, restaurantTest1);
    public static Vote vote8Admin = new Vote(8, LocalDate.now().minusDays(1), admin, restaurantTest3);
    public static Vote vote9User2 = new Vote(9,LocalDate.now().minusDays(1), user2, restaurantTest2);
    public static Vote vote10Admin = new Vote(10, LocalDate.now(), admin, restaurantTest1);

}
