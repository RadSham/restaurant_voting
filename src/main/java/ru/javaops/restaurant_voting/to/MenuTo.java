package ru.javaops.restaurant_voting.to;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.restaurant_voting.HasId;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class MenuTo extends NamedTo implements HasId {

    LocalDate date;

    @NotBlank
    Integer restaurantId;

    public MenuTo(Integer id, String name, Integer restaurantId) {
        super(id, name);
        this.date = LocalDate.now();
        this.restaurantId = restaurantId;
    }

    public MenuTo(Integer id, String name, LocalDate date, Integer restaurantId) {
        super(id, name);
        this.date = date;
        this.restaurantId = restaurantId;
    }
}
