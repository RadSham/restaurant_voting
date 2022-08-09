package ru.javaops.restaurant_voting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.model.User;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {

    LocalDate date;

    @NotBlank
    User user;

    @NotBlank
    Restaurant restaurant;

    public VoteTo(LocalDate date, User user, Restaurant restaurant) {
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public VoteTo(int id, LocalDate date, User user, Restaurant restaurant) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }
}
