package com.hiberus.heroes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.mapper.HeroMapper;
import com.hiberus.heroes.mapper.HeroMapperImpl;
import com.hiberus.heroes.service.impl.HeroService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class HeroControllerTest {


    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private HeroService heroService;

    @Spy
    private HeroMapper heroMapper = new HeroMapperImpl();


    @Test
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/heroes")
                .with(user("admin").password("admin"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/heroes/1")
                .with(user("admin").password("admin"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void findByIdNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/heroes/8")
                .with(user("admin").password("admin"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    void findAllUnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/heroes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(401))
                .andReturn();
    }


    @Test
    void saveCreatedStatusResponse() throws Exception {
        HeroDTO heroDto = this.generateHeroDto();
        String hero = new ObjectMapper().writeValueAsString(heroDto);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("admin").password("admin"))
                .content(hero)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andReturn();
    }

    @Test
    void updateHeroInvalidRequest() throws Exception {
        HeroDTO heroDto = this.generateHeroDto();
        heroDto.setDurability(-1);
        String hero = new ObjectMapper().writeValueAsString(heroDto);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/heroes/2")
                .with(user("admin").password("admin"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(hero))
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    void updateHeroValidRequest() throws Exception {
        HeroDTO heroDto = this.generateHeroDto();
        String hero = new ObjectMapper().writeValueAsString(heroDto);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/heroes/2")
                .with(user("admin").password("admin"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(hero))
                .andExpect(status().is(200))
                .andReturn();
    }

    private HeroDTO generateHeroDto() {
        return HeroDTO.builder()
                .name("HERO_NAME_TEST")
                .description("HERO_DESCRIPTION_TEST")
                .intelligence(10)
                .speed(11)
                .durability(12)
                .combat(13)
                .strength(14)
                .power(15)
                .build();
    }
}