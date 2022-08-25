package ru.javaops.restaurant_voting.to;
import lombok.*;
import lombok.experimental.NonFinal;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuTo extends NamedTo {

    @NonFinal
    LocalDate date;

    int restaurantId;

    public MenuTo(Integer id, String name, LocalDate date, int restaurantId) {
        super(id, name);
        this.date = date;
        this.restaurantId = restaurantId;
    }
}
