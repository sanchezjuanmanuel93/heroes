package com.hiberus.heroes.mapper;

import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.model.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HeroMapper {

    @Mapping(target = "intelligence", source = "powerstats.intelligence")
    @Mapping(target = "strength", source = "powerstats.strength")
    @Mapping(target = "speed", source = "powerstats.speed")
    @Mapping(target = "durability", source = "powerstats.durability")
    @Mapping(target = "power", source = "powerstats.power")
    @Mapping(target = "combat", source = "powerstats.combat")
    HeroDTO heroToHeroDTO(Hero hero);


    @Mapping(source = "id", target = "powerstats.id")
    @Mapping(source = "intelligence", target = "powerstats.intelligence")
    @Mapping(source = "strength", target = "powerstats.strength")
    @Mapping(source = "speed", target = "powerstats.speed")
    @Mapping(source = "durability", target = "powerstats.durability")
    @Mapping(source = "power", target = "powerstats.power")
    @Mapping(source = "combat", target = "powerstats.combat")
    Hero heroDTOtoHero(HeroDTO hero);


}
