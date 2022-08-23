package ru.javaops.restaurant_voting.to;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishTo extends NamedTo{

    Double price;

    Integer restaurantId;

    public DishTo(String name, Double price, Integer restaurantId) {
        super(null, name);
        this.price = price;
        this.restaurantId = restaurantId;
    }

}
