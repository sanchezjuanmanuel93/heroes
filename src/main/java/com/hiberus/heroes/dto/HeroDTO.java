package com.hiberus.heroes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroDTO {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 5, max = 500)
    private String description;

    @Min(1)
    @Max(100)
    @NotNull
    private Integer intelligence;

    @Min(1)
    @Max(100)
    @NotNull
    private Integer strength;

    @Min(1)
    @Max(100)
    @NotNull
    private Integer speed;

    @Min(1)
    @Max(100)
    @NotNull
    private Integer durability;

    @Min(1)
    @Max(100)
    @NotNull
    private Integer power;

    @Min(1)
    @Max(100)
    @NotNull
    private Integer combat;
}
