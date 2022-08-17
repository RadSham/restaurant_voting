package ru.javaops.restaurant_voting.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.service.MenuService;
import ru.javaops.restaurant_voting.to.MenuTo;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminMenuController {

    MenuRepository menuRepository;
    MenuService menuService;

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


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete menu {}", id);
        menuRepository.deleteExisted(id);
    }

    @PostMapping
    @Operation(summary = "Create menu")
    public ResponseEntity<Menu> createWithLocation(@RequestParam MenuTo menuTo) {
        Menu menu = menuService.saveFromTo(menuTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(menu.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(menu);
    }

    //TODO: finish update method
    @PutMapping(value = "/{id}")
    @Operation(summary = "Update menu")
    public Menu update(@Valid @RequestBody Menu menu, @PathVariable int id) {
        log.info("update menu {} with id {}", menu.getId(), id);
        assureIdConsistent(menu, id);
        return menuRepository.save(menu);
    }

}
