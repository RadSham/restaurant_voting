package ru.javaops.restaurant_voting.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote {
    private Date date;

    private User user;

    private Restaurant restaurant;
}
