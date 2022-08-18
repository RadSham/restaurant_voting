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
        return dishRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get dish by id")
    public ResponseEntity<Dish> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(dishRepository.findById(id));
    }

    /*@GetMapping("/restaurant/{id}")
    @Operation(summary = "Get all votes by restaurant")
    public List<Dish> getByRestaurant(@PathVariable int id) {
        log.info("get by restaurant{}", id);
        return menuRepository.getByRestaurant(id);
    }*/


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete dish")
    public void delete(@PathVariable int id) {
        log.info("delete menu {}", id);
        dishRepository.deleteExisted(id);
    }

    //TODO: finish create method
    @PostMapping
    @Operation(summary = "Create dish")
    public ResponseEntity<Dish> createWithLocation(@RequestBody DishTo dishTo) {
        Dish dish = dishService.saveFromTo(dishTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(dish.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(dish);
    }

    //TODO: finish update method
    @PutMapping(value = "/{id}")
    @Operation(summary = "Update dish")
    public Dish update(@Valid @RequestBody Dish dish, @PathVariable int id) {
        log.info("update menu {} with id {}", dish.getId(), id);
        assureIdConsistent(dish, id);
        return dishRepository.save(dish);
    }

}
