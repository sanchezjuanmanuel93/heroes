package com.hiberus.heroes.controller;


import com.hiberus.heroes.model.Hero;
import com.hiberus.heroes.service.IHeroService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/heroes")
@Slf4j
@AllArgsConstructor
public class HeroController {

    private final IHeroService heroService;

    @GetMapping()
    public ResponseEntity<Collection<Hero>> findAll() {
        log.info("::: findAll Heroes execution :::");
        Hero hero = new Hero();
        hero.setName("Juanma");
        heroService.save(hero);
        Collection<Hero> heroes = heroService.findAll();
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Hero> save(@Valid @RequestBody Hero hero) {
        Hero heroCreated = heroService.save(hero);
        return new ResponseEntity<>(heroCreated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        Hero hero = heroService.findById(id).orElseThrow();
        heroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Hero> update(@Valid @RequestBody Hero hero) {
        Hero heroUpdated = heroService.save(hero);
        return new ResponseEntity<>(heroUpdated, HttpStatus.OK);
    }
}
