package com.mozochek.repository;

import com.mozochek.entity.SportDiscipline;
import org.springframework.data.repository.CrudRepository;

public interface SportDisciplineRepository extends CrudRepository<SportDiscipline, Integer> {

    SportDiscipline findByName(String name);
}
