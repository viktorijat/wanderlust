package com.wanderlust.contest.controllers;

import com.wanderlust.contest.model.Contestant;
import com.wanderlust.contest.repository.ReactiveContestantRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/contestants")
@CrossOrigin
@Component
public class ContestantController {

    @Autowired
    private ReactiveContestantRepository contestantRepository;


    @GetMapping
    public Flux<Contestant> getAllContestants() {
        return contestantRepository.findAll();
    }

    @PostMapping
    public Mono<Contestant> createContestant(@Valid @RequestBody Contestant contestant) {
        return contestantRepository.save(contestant);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Contestant>> getContestant(@PathVariable(value = "id") String id) {
        return contestantRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Contestant>> getContestant(@PathVariable(value = "id") String id,
                                                          @Valid @RequestBody Contestant contestant) {
        return contestantRepository.findById(id)
                .flatMap(existingContestant -> {
                    existingContestant.setEmail(contestant.getEmail());
                    existingContestant.setUsername(contestant.getUsername());
                    return contestantRepository.save(existingContestant);
                })
                .map(updatedContestant -> new ResponseEntity<>(updatedContestant, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/tweets/{id}")
    public Mono<ResponseEntity<Void>> deleteContestant(@PathVariable(value = "id") String id) {

        return contestantRepository.findById(id)
                .flatMap(existingContestant ->
                        contestantRepository.delete(existingContestant)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
