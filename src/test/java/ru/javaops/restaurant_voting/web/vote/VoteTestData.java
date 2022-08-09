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
    public static final String USER_MAIL = "user@yandex.ru";
    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);

    public static final int RESTAURANT_ID_1 = 1;
    public static final int RESTAURANT_ID_2 = 2;
    public static final int RESTAURANT_ID_3 = 3;
    public static Restaurant restaurantTest1 = new Restaurant(RESTAURANT_ID_1, "ALPHA");
    public static Restaurant restaurantTest2 = new Restaurant(RESTAURANT_ID_2, "BETA");
    public static Restaurant restaurantTest3 = new Restaurant(RESTAURANT_ID_3, "GAMMA");

    public static Vote userVote1 = new Vote(1, LocalDate.now().minusDays(3), user, restaurantTest3);
    public static Vote userVote2 = new Vote(LocalDate.now().minusDays(2), user, restaurantTest2);
    public static Vote userVote3 = new Vote(LocalDate.now().minusDays(1), user, restaurantTest1);


}
