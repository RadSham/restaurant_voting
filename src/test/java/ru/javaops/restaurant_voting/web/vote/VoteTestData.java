package ru.javaops.restaurant_voting.web.vote;


import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.Map;

import static ru.javaops.restaurant_voting.web.restaurant.RestaurantTestData.*;
import static ru.javaops.restaurant_voting.web.user.UserTestData.*;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "id");

    public static Vote vote1User = new Vote(1, LocalDate.now().minusDays(3), user, restaurantTest3);
    public static Vote vote2Admin = new Vote(2, LocalDate.now().minusDays(3), admin, restaurantTest2);
    public static Vote vote3User2 = new Vote(3, LocalDate.now().minusDays(3), user2, restaurantTest1);
    public static Vote vote4User = new Vote(4, LocalDate.now().minusDays(2), user, restaurantTest2);
    public static Vote vote5Admin = new Vote(5, LocalDate.now().minusDays(2), admin, restaurantTest1);
    public static Vote vote6User2 = new Vote(6, LocalDate.now().minusDays(2), user2, restaurantTest3);
    public static Vote vote7User = new Vote(7, LocalDate.now().minusDays(1), user, restaurantTest1);
    public static Vote vote8Admin = new Vote(8, LocalDate.now().minusDays(1), admin, restaurantTest3);
    public static Vote vote9User2 = new Vote(9, LocalDate.now().minusDays(1), user2, restaurantTest2);
    public static Vote vote10Admin = new Vote(10, LocalDate.now(), admin, restaurantTest1);


    public static final Map<Integer, Vote> votes = Map.of(1, vote1User, 2, vote2Admin,
            3, vote3User2, 4, vote4User, 5, vote5Admin, 6, vote6User2,
            7, vote7User, 8, vote8Admin, 9, vote9User2, 10, vote10Admin);


    public static Vote getUpdatedVote(Vote vote, int restaurantId) {
        System.out.println("votees " + vote);
        return new Vote(vote.getDate(), vote.getUser(), restaurants.get(restaurantId));
    }

}
