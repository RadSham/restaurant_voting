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
    int userId;

    @NotBlank
    int restaurantId;

    public VoteTo(int id, LocalDate date, int userId, int restaurantId) {
        super(id);
        this.date = date;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public VoteTo(LocalDate date, int userId, int restaurantId) {
        this.date = date;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }
}
