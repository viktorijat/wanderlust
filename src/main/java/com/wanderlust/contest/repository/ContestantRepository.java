package com.wanderlust.contest.repository;

import com.wanderlust.contest.model.Contestant;
import org.springframework.data.repository.CrudRepository;

public interface ContestantRepository extends CrudRepository<Contestant, Long> {
}
