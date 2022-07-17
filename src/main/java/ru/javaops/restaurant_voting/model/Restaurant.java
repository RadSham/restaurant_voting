package ru.javaops.restaurant_voting.model;

import java.util.List;

public class Restaurant extends NamedEntity {

    private List<Dish> dishes;

    private List<Menu> menu;

    private List<Vote> votes;

}
