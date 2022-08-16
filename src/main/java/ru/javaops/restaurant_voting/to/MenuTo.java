package ru.javaops.restaurant_voting.to;
import ru.javaops.restaurant_voting.HasId;
import javax.validation.constraints.NotBlank;

public class MenuTo extends NamedTo implements HasId {

    @NotBlank
    Integer restaurantId;

    public MenuTo(Integer id, String name, Integer restaurantId) {
        super(id, name);
        this.restaurantId = restaurantId;
    }
}
