package ru.javaops.restaurant_voting.web.dish;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.repository.DishRepository;
import ru.javaops.restaurant_voting.service.DishService;
import ru.javaops.restaurant_voting.to.DishTo;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminDishController {

    DishRepository dishRepository;
    DishService dishService;

    static final String REST_URL = "/api/admin/dishes";

    @GetMapping
    @Operation(summary = "Get all dishes")
    public List<Dish> getAll() {
        log.info("getAll");
        return dishRepository.findAll(Sort.by(Sort.Direction.ASC, "restaurant"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get dish by id")
    public ResponseEntity<Dish> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(dishRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete dish")
    public void delete(@PathVariable int id) {
        log.info("delete menu {}", id);
        dishRepository.deleteExisted(id);
    }

    @PostMapping
    @Operation(summary = "Create dish")
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo) {
        log.info("create dish {}", dishTo);
        Dish dish = dishService.createNewFromTo(dishTo);
        checkNew(dish);
        dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(dish.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(dish);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update dish")
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id) {
        log.info("update dish {} with id {}", dishTo.getId(), id);
        Dish dish = dishService.updateFromTo(dishTo);
        checkNew(dish);
        assureIdConsistent(dish, id);
        dishRepository.save(dish);
    }

}
