package ru.javaops.restaurant_voting.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @OrderBy("name DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Dish> dishes;

    public Restaurant(Integer id, String name) {
        super(id, name);
        this.dishes = null;
    }

    public Restaurant(Integer id, String name, Dish... dishes) {
        super(id, name);
        this.dishes = List.of(dishes);
    }
}
