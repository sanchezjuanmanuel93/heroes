package com.hiberus.heroes.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
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

    @Size(min = 5, max = 255)
    private String description;

}
