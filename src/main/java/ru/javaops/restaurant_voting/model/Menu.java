package ru.javaops.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "restaurant_id"}, name = "menu_idx")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends NamedEntity {


    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;

    public Menu(String name,LocalDate date, Restaurant restaurant) {
        this(null, name, date, restaurant, (Dish) null);
    }

    public Menu(String name, LocalDate date, Restaurant restaurant, Dish... dishes) {
        this(null, name, date, restaurant, dishes);
    }

    public Menu(Integer id, String name, LocalDate date, Restaurant restaurant, Dish... dishes) {
        this(id, name, date, restaurant, Arrays.asList(dishes));
    }

    public Menu(Integer id, String name, LocalDate date, Restaurant restaurant, Collection<Dish> dishes) {
        super(id, name);
        this.date = date;
        this.restaurant = restaurant;
        //this.dishes = (List<Dish>) dishes;
    }

    public Menu(String name, LocalDate date, Restaurant restaurant, Collection<Dish> dishes) {
        super(null, name);
        this.date = date;
        this.restaurant = restaurant;
        //this.dishes = (List<Dish>) dishes;
    }
}
