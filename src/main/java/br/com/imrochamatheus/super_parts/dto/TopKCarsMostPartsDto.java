package br.com.imrochamatheus.super_parts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopKCarsMostPartsDto {
    private String carModel;
    private long partsQuantity;
}
