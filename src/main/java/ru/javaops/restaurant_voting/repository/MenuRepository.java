package ru.javaops.restaurant_voting.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javaops.restaurant_voting.model.Menu;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu>{
    @Query("SELECT m FROM Menu m WHERE m.restaurant.id = :restaurantId ORDER BY m.id")
    List<Menu> getByRestaurant(@Param("restaurantId") int restaurantId);
}
