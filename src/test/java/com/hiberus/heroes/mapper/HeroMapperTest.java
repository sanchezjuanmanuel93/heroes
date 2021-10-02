package com.hiberus.heroes.mapper;


import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.model.Hero;
import com.hiberus.heroes.model.Powerstats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HeroMapperTest {

    private static HeroMapper heroMapper;

    @BeforeEach
    public void setUp() {
        heroMapper = new HeroMapperImpl();
    }

    @Test
    public void givenHeroDTOFromHero_whenMaps_thenCorrect() {
        HeroDTO dto = this.generateHeroDTO();

        Hero hero = heroMapper.heroDTOtoHero(dto);

        assertAll(
                () -> {
                    assertEquals(dto.getId(), hero.getId());
                    assertEquals(dto.getName(), hero.getName());
                    assertEquals(dto.getDescription(), hero.getDescription());
                    assertEquals(dto.getId(), hero.getPowerstats().getId());
                    assertEquals(dto.getCombat(), hero.getPowerstats().getCombat());
                    assertEquals(dto.getSpeed(), hero.getPowerstats().getSpeed());
                    assertEquals(dto.getDurability(), hero.getPowerstats().getDurability());
                    assertEquals(dto.getIntelligence(), hero.getPowerstats().getIntelligence());
                    assertEquals(dto.getStrength(), hero.getPowerstats().getStrength());
                    assertEquals(dto.getPower(), hero.getPowerstats().getPower());
                }
        );
    }

    @Test
    public void givenHeroFromHeroDTO_whenMaps_thenCorrect() {
        Hero hero = this.generateHero();
        hero.setPowerstats(this.generatePowerstats(hero));

        HeroDTO dto = heroMapper.heroToHeroDTO(hero);

        assertAll(
                () -> {
                    assertEquals(dto.getId(), hero.getId());
                    assertEquals(dto.getName(), hero.getName());
                    assertEquals(dto.getDescription(), hero.getDescription());
                    assertEquals(dto.getId(), hero.getPowerstats().getId());
                    assertEquals(dto.getCombat(), hero.getPowerstats().getCombat());
                    assertEquals(dto.getSpeed(), hero.getPowerstats().getSpeed());
                    assertEquals(dto.getDurability(), hero.getPowerstats().getDurability());
                    assertEquals(dto.getIntelligence(), hero.getPowerstats().getIntelligence());
                    assertEquals(dto.getStrength(), hero.getPowerstats().getStrength());
                    assertEquals(dto.getPower(), hero.getPowerstats().getPower());
                }
        );

    }

    private Hero generateHero() {
        return Hero.builder().id(1l).name("HERO").description("HERO DESCRIPTION").build();
    }

    private Powerstats generatePowerstats(Hero hero) {
        return Powerstats.builder().id(hero.getId()).strength(10).power(11).durability(12).intelligence(13).combat(13)
                .speed(14).build();
    }

    private HeroDTO generateHeroDTO() {
        return HeroDTO.builder().id(1l).name("HERO").description("HERO DESCRIPTION")
                .combat(10).durability(11).intelligence(12).power(13).speed(14).strength(15)
                .build();
    }
}
