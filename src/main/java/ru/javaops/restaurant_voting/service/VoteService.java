package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.repository.VoteRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class VoteService {

    protected VoteRepository voteRepository;
    protected RestaurantRepository restaurantRepository;

    //TODO: without sout method works incorrect
    public Vote update(Vote vote, int restaurantId) {
        vote.setRestaurant(restaurantRepository.getById(restaurantId));
        System.out.println("vote.getRest " + vote.getRestaurant());
        return voteRepository.save(vote);
    }


}
