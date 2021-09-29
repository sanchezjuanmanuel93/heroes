package com.hiberus.heroes.mapper;

import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.model.Hero;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HeroMapper {

    HeroDTO heroToHeroDTO(Hero hero);

    Hero heroDTOtoHero(HeroDTO hero);
}
