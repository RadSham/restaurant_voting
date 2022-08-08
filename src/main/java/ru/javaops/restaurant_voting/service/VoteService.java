package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
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

    //TODO: to finish update method
    public Vote update(User user, int restaurantId){
        Vote vote = voteRepository.getByUserAndDate(user.id(), LocalDate.now());
        vote.setRestaurant(restaurantRepository.getById(restaurantId));
        return voteRepository.save(vote);
    }
}
