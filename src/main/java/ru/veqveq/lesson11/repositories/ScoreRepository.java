package ru.veqveq.lesson11.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.veqveq.lesson11.entities.Score;
import ru.veqveq.lesson11.entities.User;

public interface ScoreRepository extends CrudRepository <Score,Long> {
    Score findByUser(User user);
}
