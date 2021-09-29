package com.hiberus.heroes.mapper;


import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.model.Hero;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HeroMapperTest {

    private static HeroMapper heroMapper;

    @BeforeEach
    public void setUp() {
        heroMapper = new HeroMapperImpl();
    }

    @Test
    public void givenHeroDTOFromHero_whenMaps_thenCorrect() {
        HeroDTO dto = new HeroDTO().builder().id(1l).name("HERO").description("HERO DESCRIPTION").build();

        Hero hero = heroMapper.heroDTOtoHero(dto);

        Assert.assertEquals(dto.getId(), hero.getId());
        Assert.assertEquals(dto.getName(), hero.getName());
        Assert.assertEquals(dto.getDescription(), hero.getDescription());
    }

    @Test
    public void givenHeroFromHeroDTO_whenMaps_thenCorrect() {
        Hero hero = new Hero().builder().id(1l).name("HERO").description("HERO DESCRIPTION").build();

        HeroDTO dto = heroMapper.heroToHeroDTO(hero);

        Assert.assertEquals(dto.getId(), hero.getId());
        Assert.assertEquals(dto.getName(), hero.getName());
        Assert.assertEquals(dto.getDescription(), hero.getDescription());
    }
}
