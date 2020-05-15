package com.mozochek.repository;

import com.mozochek.entity.SportDiscipline;
import com.mozochek.entity.SportKind;
import org.springframework.data.repository.CrudRepository;

public interface SportDisciplineRepository extends CrudRepository<SportDiscipline, Integer> {

    SportDiscipline findByName(String name);

    SportDiscipline findByNameAndCodeAndSportKind(String name, String code, SportKind sportKind);

    Iterable<SportDiscipline> findBySportKindId(Integer sportKindId);
}
