package ru.javaops.restaurant_voting.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.repository.MenuRepository;

import java.util.List;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminMenuController {

    MenuRepository menuRepository;

    static final String REST_URL = "/api/admin/menus";

    @GetMapping("/{id}")
    public ResponseEntity<Menu> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(menuRepository.findById(id));
    }


    @GetMapping
    @Operation(summary = "Get all menus")
    public List<Menu> getAll() {
        log.info("getAll");
        return menuRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/restaurant/{id}")
    @Operation(summary = "Get all votes by restaurant")
    public List<Menu> getByRestaurant(@RequestParam int restaurantId) {
        log.info("get by restaurant{}", restaurantId);
        return menuRepository.getByRestaurant(restaurantId);
    }

}
