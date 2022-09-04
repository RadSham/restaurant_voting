package ru.javaops.restaurant_voting.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "restaurant_id"}, name = "vote_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseEntity {

    @Column(name = "date", nullable = false)
    @NonNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    // get only restaurantId: https://stackoverflow.com/questions/33475222/spring-boot-jpa-json-without-nested-object-with-onetomany-relation
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    // get only restaurantId: https://stackoverflow.com/questions/33475222/spring-boot-jpa-json-without-nested-object-with-onetomany-relation
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Vote(@NonNull LocalDate date, User user) {
        this(null, date, user, null);
    }

    public Vote(Integer id, @NonNull LocalDate date, User user, Restaurant restaurant) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(Vote vote) {
        this(vote.getId(), vote.getDate(), vote.getUser(), vote.getRestaurant());
    }
}
