package com.mozochek.repository;

import com.mozochek.entity.Tournament;
import org.springframework.data.repository.CrudRepository;

public interface TournamentRepository extends CrudRepository<Tournament, Integer> {

    Iterable<Tournament> findAllByOrderByIdAsc();
}
