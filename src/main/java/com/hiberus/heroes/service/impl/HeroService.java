package com.hiberus.heroes.service.impl;

import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.mapper.HeroMapper;
import com.hiberus.heroes.model.Hero;
import com.hiberus.heroes.repository.HeroRepository;
import com.hiberus.heroes.service.IHeroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class HeroService implements IHeroService {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    @Override
    public Collection<HeroDTO> findAll() {
        return heroRepository.findAll().stream()
                .map(hero -> heroMapper.heroToHeroDTO(hero))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HeroDTO> findById(Long id) {
        Optional<Hero> hero = heroRepository.findById(id);
        if (Boolean.FALSE.equals(hero.isPresent())) {
            return Optional.empty();
        }

        return Optional.ofNullable(heroMapper.heroToHeroDTO(hero.get()));
    }

    @Override
    public Collection<HeroDTO> findByName(String name) {
        return heroRepository.findByNameIsContaining(name).stream()
                .map(hero -> heroMapper.heroToHeroDTO(hero))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(HeroDTO heroDTO) {
        Hero hero = heroMapper.heroDTOtoHero(heroDTO);
        heroRepository.delete(hero);
    }

    @Override
    public HeroDTO save(HeroDTO heroDto) {
        Hero hero = heroMapper.heroDTOtoHero(heroDto);
        hero.getPowerstats().setHero(hero);
        return heroMapper.heroToHeroDTO(heroRepository.save(hero));
    }


}
