package ru.javaops.restaurant_voting.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "restaurant_id"}, name = "dish_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Dish extends NamedEntity {

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @ToString.Exclude
    private Restaurant restaurant;

}
