package com.mozochek.repository;

import com.mozochek.entity.Human;
import org.springframework.data.repository.CrudRepository;

public interface HumanRepository extends CrudRepository<Human, Integer> {

}
