package com.hiberus.heroes.service.impl;

import com.hiberus.heroes.expection.NotFoundException;
import com.hiberus.heroes.model.Hero;
import com.hiberus.heroes.model.HeroBuilder;
import com.hiberus.heroes.repository.HeroRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    @Mock
    HeroRepository heroRepository;

    @InjectMocks
    private HeroService heroService;

    private Hero hero;
    private static final Long HERO_ID_TEST = 1l;
    private static final String HERO_NAME_TEST = "SPIDER MAN";


    @BeforeEach
    public void setUp() {
        hero = HeroBuilder.newHeroBuilder().withId(HERO_ID_TEST).withName(HERO_NAME_TEST).build();
        when(heroRepository.findById(HERO_ID_TEST)).thenReturn(java.util.Optional.ofNullable(hero));
    }

    @Test
    void shouldReturnHeroById() {
        Hero heroTest = heroService.findById(HERO_ID_TEST).orElseThrow(() -> new NotFoundException("asd"));
        Assert.assertEquals(heroTest.getId(), HERO_ID_TEST);
    }

}
