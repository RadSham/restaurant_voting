package ru.javaops.restaurant_voting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.to.UserTo;
import ru.javaops.restaurant_voting.util.UserUtil;
import ru.javaops.restaurant_voting.web.AuthUser;
import ru.javaops.restaurant_voting.web.user.AbstractUserController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantController extends AbstractRestaurantController {
    static final String REST_URL = "/api/restaurants";


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return super.getAll();
    }

}