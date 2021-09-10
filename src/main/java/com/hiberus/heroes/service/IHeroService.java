package com.hiberus.heroes.service;

import com.hiberus.heroes.model.Hero;

import java.util.Collection;
import java.util.Optional;

public interface IHeroService {

    Collection<Hero> findAll();

    Optional<Hero> findById(Long id);

    Optional<Hero> findByName(String id);

    void deleteById(Long id);

    Hero save(Hero hero);

}
