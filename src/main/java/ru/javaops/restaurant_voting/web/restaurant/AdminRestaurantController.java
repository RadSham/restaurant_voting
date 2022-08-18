package ru.javaops.restaurant_voting.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.service.MenuService;
import ru.javaops.restaurant_voting.to.MenuTo;
import ru.javaops.restaurant_voting.to.RestaurantTo;
import ru.javaops.restaurant_voting.util.RestaurantUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestaurantController extends AbstractRestaurantController {
    static final String REST_URL = "/api/admin/restaurants";

    MenuService menuService;

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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete restaurant")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    @PostMapping
    @Operation(summary = "Create restaurant")
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestParam RestaurantTo name) {
        log.info("create restaurant {}", name);
        checkNew(name);
        Restaurant created = repository.save(RestaurantUtil.createNewFromTo(name));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update restaurant")
    public void update(@Valid @RequestParam RestaurantTo restaurantTo, @PathVariable int id) {
        log.info("update restaurant {}", id);
        Restaurant restaurant = RestaurantUtil.createNewFromTo(restaurantTo);
        assureIdConsistent(restaurant, id);
        repository.save(restaurant);
    }
}
