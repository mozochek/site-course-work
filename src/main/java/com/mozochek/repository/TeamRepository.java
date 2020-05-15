package com.mozochek.repository;

import com.mozochek.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    Iterable<Team> findAllByTournamentIdOrderByIdDesc(Integer tournamentId);
}
