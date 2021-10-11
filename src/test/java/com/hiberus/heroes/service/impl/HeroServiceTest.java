package com.hiberus.heroes.service.impl;

import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.expection.NotFoundException;
import com.hiberus.heroes.mapper.HeroMapper;
import com.hiberus.heroes.mapper.HeroMapperImpl;
import com.hiberus.heroes.model.Hero;
import com.hiberus.heroes.model.Powerstats;
import com.hiberus.heroes.repository.HeroRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void shouldReturnBestHero() {
        List<Hero> heroes = this.generateHeroList();
        when(heroRepository.findAll()).thenReturn(heroes);

        HeroDTO heroTest = heroService.findBestHero().orElseThrow(() -> new NotFoundException("Not found Exception"));
        Hero hero = heroMapper.heroDTOtoHero(heroTest);
        assertAll(
                () -> {
                    assertEquals(hero.getId(), 3l);
                    assertEquals(hero.getTotalPower(), 180);
                }
        );
    }


    private List<Hero> generateHeroList() {
        return Stream.of(
                Hero.builder().id(1l).name("HERO 1").description("HERO 1 DESC")
                        .powerstats(
                                Powerstats.builder()
                                        .power(10).speed(10).combat(10).intelligence(10).durability(10).strength(10)
                                        .build())
                        .build(),
                Hero.builder().id(2l).name("HERO 2").description("HERO 2 DESC").powerstats(
                        Powerstats.builder().power(20).speed(20).combat(20).intelligence(20).durability(20).strength(20)
                                .build())
                        .build(),
                Hero.builder().id(3l).name("HERO 3").description("HERO 3 DESC").powerstats(
                        Powerstats.builder().power(30).speed(30).combat(30).intelligence(30).durability(30).strength(30)
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

    private Hero generateHero() {
        return Hero.builder().id(HERO_ID_TEST).name(HERO_NAME_TEST).description(HERO_DESCRIPTION_TEST).build();
    }

}
