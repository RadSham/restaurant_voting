package ru.javaops.restaurant_voting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.repository.UserRepository;
import ru.javaops.restaurant_voting.util.UserUtil;
import ru.javaops.restaurant_voting.web.user.UniqueMailValidator;

import java.util.List;

@Slf4j
public abstract class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository repository;

    public ResponseEntity<Restaurant> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }


    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

}