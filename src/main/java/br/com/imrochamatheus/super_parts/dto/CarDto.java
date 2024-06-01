package br.com.imrochamatheus.super_parts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {
    private long id;

    @NotBlank(message = "model is required")
    @Size(min = 2, max = 255, message = "model must must be of 2 - 255 characters")
    private String model;

    @NotBlank(message = "producer is required")
    @Size(min = 3, max = 255, message = "producer must must be of 3 - 255 characters")
    private String producer;

    @NotBlank(message = "code is required")
    @Size(min = 3, max = 255, message = "code must be of 3 -  255 characters")
    private String code;
}
