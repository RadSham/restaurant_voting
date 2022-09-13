package ru.javaops.restaurant_voting.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.VoteRepository;
import ru.javaops.restaurant_voting.service.VoteService;
import ru.javaops.restaurant_voting.web.AuthUser;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javaops.restaurant_voting.util.validation.ValidationUtil.checkTime;


@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {

    protected VoteRepository voteRepository;
    protected VoteService voteService;

    static final String REST_URL = "/api/votes";

    @GetMapping("/{id}")
    @Operation(summary = "Get vote by id")
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
    @Operation(summary = "Get own votes [history]")
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

    @PostMapping
    @Operation(summary = "Create vote")
    public ResponseEntity<Vote> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        log.info("create user {} vote for restaurant {}", authUser.id(), restaurantId);
        Vote vote = new Vote(LocalDate.now(), authUser.getUser());
        Vote newVote = voteService.save(vote, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newVote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(newVote);
    }

    @PutMapping
    @Operation(summary = "Update vote")
    public void update(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        log.info("update user {} vote for restaurant {}", authUser.id(), restaurantId);
        checkTime();
        voteService.update(authUser.id(), restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete vote")
    public void delete(@PathVariable int id) {
        log.info("delete menu {}", id);
        voteRepository.deleteExisted(id);
    }

}
