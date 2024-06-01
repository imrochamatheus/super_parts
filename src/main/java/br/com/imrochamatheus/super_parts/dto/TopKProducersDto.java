package br.com.imrochamatheus.super_parts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopKProducersDto {
    private String name;
    private long quantity;
}
