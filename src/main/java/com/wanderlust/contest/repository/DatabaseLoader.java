package com.wanderlust.contest.repository;

import com.wanderlust.contest.model.Contestant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ContestantRepository repository;

    @Autowired
    public DatabaseLoader(ContestantRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        this.repository.save(new Contestant(1L, "FrodoBaggins", "f.baggins@gmail.com"));
    }
}
