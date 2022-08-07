package ru.javaops.restaurant_voting.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.VoteRepository;
import ru.javaops.restaurant_voting.to.UserTo;
import ru.javaops.restaurant_voting.util.UserUtil;
import ru.javaops.restaurant_voting.web.AuthUser;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoteController {

    @Autowired
    protected VoteRepository repository;
    static final String REST_URL = "/api/votes";

    @GetMapping("/{id}")
    public ResponseEntity<Vote> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }


    @GetMapping
    public List<Vote> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    //TODO: get votes by user
    @GetMapping("/user")
    @Operation(summary = "Get by user")
    public List<Vote> getByUser(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get by user{}", authUser);
        return repository.getByUser(authUser.id());
    }

    @GetMapping("/useranddate")
    @Operation(summary = "Get by user and date")
    public ResponseEntity<Vote> getByUserAndDate(@AuthenticationPrincipal AuthUser authUser,
                                                 @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get by user{} {}", authUser, date);
        return ResponseEntity.of(repository.getByUserAndDate(authUser.id(), date));
    }

    //TODO: to finish update method
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Restaurant restaurant, @AuthenticationPrincipal AuthUser authUser) {
        log.info("update {} vote", authUser.id());
        //assureIdConsistent(restaurant, id);
        //repository.save(restaurant);
    }



}
