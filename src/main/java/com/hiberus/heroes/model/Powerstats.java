package com.hiberus.heroes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "powerstats")
public class Powerstats {

    @Id
    private Long id;

    @Column(nullable = false)
    private Integer intelligence;


    @Column(nullable = false)
    private Integer strength;


    @Column(nullable = false)
    private Integer speed;


    @Column(nullable = false)
    private Integer durability;


    @Column(nullable = false)
    private Integer power;


    @Column(nullable = false)
    private Integer combat;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Hero hero;

}
