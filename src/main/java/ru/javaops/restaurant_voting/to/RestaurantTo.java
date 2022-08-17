package ru.javaops.restaurant_voting.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo{

    public RestaurantTo(Integer id, String name) {
        super(id, name);
    }

    public RestaurantTo(String name) {
        super(null, name);
    }

}
