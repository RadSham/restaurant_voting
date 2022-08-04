package ru.javaops.restaurant_voting.web.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.model.Vote;
import ru.javaops.restaurant_voting.repository.VoteRepository;

import javax.validation.Valid;

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

    //TODO: get votes by user
    @GetMapping("/user/{id}")
    public ResponseEntity<Vote> getByUserId(@Valid @RequestBody User user, @PathVariable int id) {
        log.info("get votes {} with id={}", user, id);
        assureIdConsistent(user, id);
        return ResponseEntity.of(repository.getByUserId(id));
    }
}
