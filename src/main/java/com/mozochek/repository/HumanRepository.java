package com.mozochek.repository;

import com.mozochek.entity.Human;
import com.mozochek.utils.Gender;
import org.springframework.data.repository.CrudRepository;

public interface HumanRepository extends CrudRepository<Human, Integer> {
    Iterable<Human> findAllByGender(String gender);

    Iterable<Human> findAllByGender(Gender gender);
}
