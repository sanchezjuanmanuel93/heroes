package com.hiberus.heroes.controller;


import com.hiberus.heroes.dto.HeroDTO;
import com.hiberus.heroes.expection.NotFoundException;
import com.hiberus.heroes.model.Hero;
import com.hiberus.heroes.service.IHeroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/heroes")
@Slf4j
@RequiredArgsConstructor
public class HeroController {

    private final IHeroService heroService;

    @GetMapping()
    public ResponseEntity<Collection<HeroDTO>> findAll() {
        log.info("::: findAll Heroes execution :::");
        Collection<HeroDTO> heroes = heroService.findAll();
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroDTO> findById(@PathVariable Long id) {
        log.info("::: find Hero by Id execution :::");
        HeroDTO hero = heroService.findById(id).orElseThrow(() -> new NotFoundException(Hero.class.getName()));
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HeroDTO> save(@Valid @RequestBody HeroDTO heroDTO) {
        log.info("::: save Heroes execution :::");
        HeroDTO hero = heroService.save(heroDTO);
        return new ResponseEntity<>(hero, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("::: delete Heroes execution :::");
        HeroDTO hero = heroService.findById(id).orElseThrow(() -> new NotFoundException(Hero.class.getName()));
        heroService.delete(hero);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeroDTO> update(@PathVariable Long id, @Valid @RequestBody HeroDTO heroDTO) {
        log.info("::: update Heroes execution :::");
        HeroDTO heroUpdate = heroService.findById(id)
                .orElseThrow(() -> new NotFoundException(Hero.class.getName()));
        heroDTO.setId(id);
        return ResponseEntity.ok().body(heroService.save(heroDTO));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Collection<HeroDTO>> findByName(@PathVariable String name) {
        log.info("::: findByName Heroes execution :::");
        Collection<HeroDTO> heroes = heroService.findByName(name);
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @GetMapping("/best")
    public ResponseEntity<HeroDTO> findBestHero() {
        log.info("::: find best Hero execution :::");
        HeroDTO hero = heroService.findBestHero()
                .orElseThrow(() -> new NotFoundException(Hero.class.getName()));
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }
}
