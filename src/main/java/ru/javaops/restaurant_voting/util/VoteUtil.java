package ru.javaops.restaurant_voting.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.repository.UserRepository;
import ru.javaops.restaurant_voting.to.VoteTo;

import javax.annotation.PostConstruct;
import java.sql.SQLOutput;

@UtilityClass
public class VoteUtil {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    //https://stackoverflow.com/questions/27728292/injecting-a-spring-data-rest-repository-into-a-utility-class

    User user = userRepository.getById(1);

    @PostConstruct
    public static Vote createNewVoteFromTo(VoteTo voteTo) {
        System.out.println("sout in createNewVoteFromTo, user is - " + user);
        return new Vote(null, voteTo.getDate(), userRepository.getById(voteTo.getUserId()), restaurantRepository.getById(voteTo.getRestaurantId()));
    }
}
