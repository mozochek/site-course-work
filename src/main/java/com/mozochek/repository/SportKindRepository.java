package com.mozochek.repository;

import com.mozochek.entity.SportKind;
import org.springframework.data.repository.CrudRepository;

public interface SportKindRepository extends CrudRepository<SportKind, Integer> {

    SportKind findByName(String name);
}
