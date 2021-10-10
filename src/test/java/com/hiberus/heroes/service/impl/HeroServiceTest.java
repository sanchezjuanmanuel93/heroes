package com.hiberus.heroes.service.impl;

import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.expection.NotFoundException;
import com.hiberus.heroes.mapper.HeroMapper;
import com.hiberus.heroes.mapper.HeroMapperImpl;
import com.hiberus.heroes.model.Hero;
import com.hiberus.heroes.repository.HeroRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    @Spy
    private HeroMapper heroMapper = new HeroMapperImpl();

    @Mock
    HeroRepository heroRepository;

    @InjectMocks
    private HeroService heroService;

    private static final Long HERO_ID_TEST = 1l;
    private static final String HERO_NAME_TEST = "HERO 1";
    private static final String HERO_DESCRIPTION_TEST = "HERO 1 DESC";

    private static final String HERO_FIND_BY_NAME_TEST = "HERO";


    @Test
    void shouldReturnHeroById() {
        when(heroRepository.findById(HERO_ID_TEST)).thenReturn(java.util.Optional.ofNullable(this.generateHero()));

        HeroDTO heroTest = heroService.findById(HERO_ID_TEST).orElseThrow(() -> new NotFoundException("Not found Exception"));
        Assert.assertEquals(heroTest.getId(), HERO_ID_TEST);
    }

    @Test
    void shouldReturnHeroByName() {
        when(heroRepository.findByNameIsContaining(HERO_FIND_BY_NAME_TEST)).thenReturn(this.generateHeroList());

        Collection<HeroDTO> heroes = heroService.findByName(HERO_FIND_BY_NAME_TEST);
        assertThat(heroes.size()).isEqualTo(3);
    }

    private Collection<Hero> generateHeroList() {
        Collection<Hero> heroes = new ArrayList<>();
        heroes.add(Hero.builder().id(1l).name("HERO 1").description("HERO 1 DESC").build());
        heroes.add(Hero.builder().id(2l).name("HERO 2").description("HERO 2 DESC").build());
        heroes.add(Hero.builder().id(3l).name("HERO 3").description("HERO 3 DESC").build());
        return heroes;
    }

    private Hero generateHero() {
        return Hero.builder().id(HERO_ID_TEST).name(HERO_NAME_TEST).description(HERO_DESCRIPTION_TEST).build();
    }

}
