package ru.javaops.restaurant_voting.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.VoteRepository;
import ru.javaops.restaurant_voting.web.AuthUser;

import java.time.LocalDate;
import java.util.List;

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


}
