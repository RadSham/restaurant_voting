package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.repository.VoteRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class VoteService {

    protected VoteRepository voteRepository;
    protected RestaurantRepository restaurantRepository;

    public Vote update(int userId, int restaurantId) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        Vote vote = new Vote(voteRepository.getByUserAndDate(userId, LocalDate.now()));
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }
}
