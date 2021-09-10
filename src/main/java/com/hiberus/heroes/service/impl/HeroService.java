package com.hiberus.heroes.service.impl;

import com.hiberus.heroes.model.Hero;
import com.hiberus.heroes.repository.HeroRepository;
import com.hiberus.heroes.service.IHeroService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class HeroService implements IHeroService {

    private final HeroRepository heroRepository;

    @Override
    public Collection<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public Optional<Hero> findById(Long id) {
        return heroRepository.findById(id);
    }

    @Override
    public Optional<Hero> findByName(String name) {
        return heroRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        heroRepository.deleteById(id);
    }

    @Override
    public Hero save(Hero hero) {
        return heroRepository.save(hero);
    }


}
