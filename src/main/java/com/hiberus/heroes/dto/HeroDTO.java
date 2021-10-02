package com.hiberus.heroes.dto;

import lombok.*;

import javax.validation.constraints.*;

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
