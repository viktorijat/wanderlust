package com.wanderlust.contest.repository;

import com.wanderlust.contest.model.Contestant;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactiveContestantRepository extends ReactiveMongoRepository<Contestant, String> {

}
