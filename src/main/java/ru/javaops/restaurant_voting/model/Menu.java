package ru.javaops.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.domain.Persistable;
import ru.javaops.restaurant_voting.HasId;
import ru.javaops.restaurant_voting.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "restaurant_id"}, name = "menu_idx")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu implements Persistable<Integer>, HasId {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // https://stackoverflow.com/a/28025008/548473
    protected Integer id;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    @NoHtml
    protected String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @OrderBy("name DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Dish> dishes;

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
        this.id = id;
        this.name = name;
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = (List<Dish>) dishes;
    }


    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public int id() {
        return HasId.super.id();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                ", date=" + date +
                ", restaurant=" + restaurant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) && Objects.equals(name, menu.name) && Objects.equals(dishes, menu.dishes) && Objects.equals(date, menu.date) && Objects.equals(restaurant, menu.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dishes, date, restaurant);
    }
}
