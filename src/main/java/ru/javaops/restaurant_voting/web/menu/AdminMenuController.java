package ru.javaops.restaurant_voting.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.restaurant_voting.model.Menu;
import ru.javaops.restaurant_voting.repository.MenuRepository;
import ru.javaops.restaurant_voting.service.MenuService;
import ru.javaops.restaurant_voting.to.MenuTo;

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

    @GetMapping
    @Operation(summary = "Get all menus")
    public List<Menu> getAll() {
        log.info("getAll");
        return menuRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get menu by id")
    public ResponseEntity<Menu> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(menuRepository.findById(id));
    }

    @GetMapping("/restaurant/{id}")
    @Operation(summary = "Get all menus by restaurant")
    public List<Menu> getByRestaurant(@PathVariable int id) {
        log.info("get by restaurant{}", id);
        return menuRepository.getByRestaurant(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete menu")
    public void delete(@PathVariable int id) {
        log.info("delete menu {}", id);
        menuRepository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create menu")
    public ResponseEntity<Menu> createWithLocation(@RequestBody MenuTo menuTo) {
        log.info("create menu {}", menuTo);
        Menu menu = menuService.saveFromTo(menuTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(menu.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(menu);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update menu name")
    public void update(@RequestBody String name, @PathVariable int id) {
        log.info("update menu {}", id);
        Menu menu = menuService.updateFromTo(name, id);
        assureIdConsistent(menu, id);
        menuRepository.save(menu);
    }
}
