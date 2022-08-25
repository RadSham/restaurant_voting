package ru.javaops.restaurant_voting.to;

import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishTo extends NamedTo{

    Double price;

    Integer restaurantId;

    Integer menuId;

    public DishTo(String name, Double price, Integer restaurantId, Integer menuId) {
        super(null, name);
        this.price = price;
        this.restaurantId = restaurantId;
        this.menuId = menuId;
    }
}
