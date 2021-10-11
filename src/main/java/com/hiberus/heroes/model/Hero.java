package com.hiberus.heroes.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hero", optional = false)
    private Powerstats powerstats;

    public Integer getTotalPower() {
        return
                this.powerstats.getIntelligence() + this.powerstats.getStrength() + this.powerstats.getSpeed()
                        + this.powerstats.getDurability() + this.powerstats.getPower() + this.powerstats.getCombat();
    }

}
