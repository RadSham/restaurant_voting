package ru.javaops.restaurant_voting.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restaurant_voting.model.Vote;

import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote>{

    Optional<Vote> getByUserId (int userId);
}
