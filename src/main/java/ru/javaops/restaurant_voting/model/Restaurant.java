package ru.javaops.restaurant_voting.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity {

    private List<Dish> dishes;

    private List<Menu> menu;

    private List<Vote> votes;

}
