package ru.javaops.restaurant_voting.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.VoteRepository;
import ru.javaops.restaurant_voting.service.VoteService;
import ru.javaops.restaurant_voting.web.AuthUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.checkTime;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {

    protected VoteRepository repository;
    protected VoteService voteService;

    static final String REST_URL = "/api/votes";

    @GetMapping("/{id}")
    public ResponseEntity<Vote> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }


    @GetMapping
    @Operation(summary = "Get all votes")
    public List<Vote> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/user")
    @Operation(summary = "Get all votes by user")
    public List<Vote> getByUser(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get by user{}", authUser);
        return repository.getByUser(authUser.id());
    }

    @GetMapping("/useranddate")
    @Operation(summary = "Get vote by user and date")
    public Vote getByUserAndDate(@AuthenticationPrincipal AuthUser authUser,
                                                 @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get by user{} {}", authUser, date);
        return repository.getByUserAndDate(authUser.id(), date);
    }

    @PutMapping
    @Operation(summary = "Update vote")
    public Vote update(@AuthenticationPrincipal AuthUser authUser, int restaurantId) {
        log.info("update user {} vote for restaurant {}", authUser.id(), restaurantId);
        checkTime();
        return voteService.update(authUser.getUser(), restaurantId);
    }


}
