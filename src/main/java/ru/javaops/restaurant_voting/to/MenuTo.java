package ru.javaops.restaurant_voting.to;
import lombok.*;
import lombok.experimental.NonFinal;
import org.springframework.lang.Nullable;
import ru.javaops.restaurant_voting.HasId;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuTo extends NamedTo {

    @NonFinal
    LocalDate date;

    int[] dishes;

    @NotNull
    int restaurantId;

    public MenuTo(Integer id, String name, LocalDate date, int[] dishes, int restaurantId) {
        super(id, name);
        this.date = date;
        this.dishes = dishes;
        this.restaurantId = restaurantId;
    }
}
