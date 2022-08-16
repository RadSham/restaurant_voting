package ru.javaops.restaurant_voting.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity {
    public Restaurant(Integer id, String name) {
        super(id, name);
    }

}
