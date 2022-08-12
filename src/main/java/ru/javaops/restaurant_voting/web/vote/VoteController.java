package ru.javaops.restaurant_voting.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.VoteRepository;
import ru.javaops.restaurant_voting.service.VoteService;
import ru.javaops.restaurant_voting.web.AuthUser;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {

    protected VoteRepository voteRepository;
    protected VoteService voteService;

    static final String REST_URL = "/api/votes";

    @GetMapping("/{id}")
    public ResponseEntity<Vote> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(voteRepository.findById(id));
    }


    @GetMapping
    @Operation(summary = "Get all votes")
    public List<Vote> getAll() {
        log.info("getAll");
        return voteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/user")
    @Operation(summary = "Get all votes by user")
    public List<Vote> getByUser(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get by user{}", authUser);
        return voteRepository.getByUser(authUser.id());
    }

    @GetMapping("/useranddate")
    @Operation(summary = "Get vote by user and date")
    public Vote getByUserAndDate(@AuthenticationPrincipal AuthUser authUser,
                                 @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get by user{} {}", authUser, date);
        return voteRepository.getByUserAndDate(authUser.id(), date);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Update vote")
    public Vote update(@AuthenticationPrincipal AuthUser authUser, int restaurantId) {
        log.info("update user {} vote for restaurant {}", authUser.id(), restaurantId);
        //TODO: uncomment checkTime() after finish update test
        //checkTime();
        return voteService.update(authUser.id(), restaurantId);
    }


}
