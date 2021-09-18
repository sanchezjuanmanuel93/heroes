package com.hiberus.heroes.model;

public class HeroBuilder {
    private Long id;
    private String name;

    private HeroBuilder() {
    }

    public static HeroBuilder newHeroBuilder() {
        return new HeroBuilder();
    }

    public HeroBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public HeroBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Hero build() {
        Hero hero = new Hero();
        hero.setId(this.id);
        hero.setName(this.name);
        return hero;
    }
}
