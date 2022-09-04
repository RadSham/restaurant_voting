package ru.javaops.restaurant_voting.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("name DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    @Nullable
    private Menu menu;

    public Restaurant(Integer id, String name) {
        super(id, name);
        this.menu = null;
    }

}
