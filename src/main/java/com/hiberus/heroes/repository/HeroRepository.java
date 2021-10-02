package com.hiberus.heroes.repository;

import com.hiberus.heroes.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    Collection<Hero> findByNameIsContaining(String name);
}
