package ru.javaops.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restaurant_voting.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote>{

    //TODO: get votes by user
    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId GROUP BY v")
    Optional<Vote> getByUser(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId AND (:date IS NULL OR v.date = :date)")
    Optional<Vote> getByUserAndDate(int userId, LocalDate date);
}
