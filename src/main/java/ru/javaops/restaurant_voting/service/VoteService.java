package ru.javaops.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.restaurant_voting.error.IllegalRequestDataException;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.repository.VoteRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class VoteService {

    VoteRepository voteRepository;
    RestaurantRepository restaurantRepository;

    public Vote update(int userId, int restaurantId) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        Vote vote = new Vote(voteRepository.getByUserAndDate(userId, LocalDate.now()));
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    public Vote save(Vote vote, int restaurantId) {
        if (voteRepository.getByUserAndDate(vote.getUser().getId(), vote.getDate()) != null)
            throw new IllegalRequestDataException("Voting is possible once a day");
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        vote.setRestaurant(restaurant);
        voteRepository.save(vote);
        System.out.println("Vote for restaurant " + vote.getRestaurant());
        return vote;
    }
}
