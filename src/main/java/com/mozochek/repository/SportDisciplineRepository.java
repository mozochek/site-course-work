package com.mozochek.repository;

import com.mozochek.entity.SportDiscipline;
import com.mozochek.entity.SportKind;
import org.springframework.data.repository.CrudRepository;

public interface SportDisciplineRepository extends CrudRepository<SportDiscipline, Integer> {

    SportDiscipline findByName(String name);

    SportDiscipline findBySportKindIdAndNameAndCode(Integer sportKind, String name, String code);

    Iterable<SportDiscipline> findBySportKindId(Integer sportKindId);
}
