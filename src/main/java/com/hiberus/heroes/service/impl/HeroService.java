package com.hiberus.heroes.service.impl;

import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.mapper.HeroMapper;
import com.hiberus.heroes.model.Hero;
import com.hiberus.heroes.repository.HeroRepository;
import com.hiberus.heroes.service.IHeroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "hero")
public class HeroService implements IHeroService {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    @Override
    @Cacheable(key = "{ #root.methodName }")
    public Collection<HeroDTO> findAll() {
        return heroRepository.findAll().stream()
                .map(hero -> heroMapper.heroToHeroDTO(hero))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "{ #root.methodName, #id }")
    public Optional<HeroDTO> findById(Long id) {
        Optional<Hero> hero = heroRepository.findById(id);
        if (Boolean.FALSE.equals(hero.isPresent())) {
            return Optional.empty();
        }

        return Optional.ofNullable(heroMapper.heroToHeroDTO(hero.get()));
    }

    @Override
    @Cacheable(key = "{ #root.methodName, #name }")
    public Collection<HeroDTO> findByName(String name) {
        return heroRepository.findByNameIsContaining(name).stream()
                .map(hero -> heroMapper.heroToHeroDTO(hero))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(key = "{ #root.methodName }" , allEntries = true)
    public void delete(HeroDTO hero) {
        heroRepository.delete(heroMapper.heroDTOtoHero(hero));
    }

    @Override
    @CacheEvict(key = "{ #root.methodName }", allEntries = true)
    public HeroDTO save(HeroDTO heroDto) {
        Hero hero = heroMapper.heroDTOtoHero(heroDto);
        return heroMapper.heroToHeroDTO(heroRepository.save(hero));
    }


}
