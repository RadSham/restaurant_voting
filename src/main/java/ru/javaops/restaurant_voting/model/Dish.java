package ru.javaops.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "menu_id"}, name = "dish_idx")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Dish extends NamedEntity {

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonBackReference
    @ToString.Exclude
    private Menu menu;

    public Dish(String name, double price) {
        this(null, name, price, null);
    }

    public Dish(String name, double price, Menu menu) {
        super(null, name);
        this.price = price;
        this.menu = menu;
    }

    public Dish(Integer id, String name, double price, Menu menu) {
        super(id, name);
        this.price = price;
        this.menu = menu;
    }

}
