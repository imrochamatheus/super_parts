package br.com.imrochamatheus.super_parts.dto;

import br.com.imrochamatheus.super_parts.validation.OnCreateCar;
import br.com.imrochamatheus.super_parts.validation.OnUpdateCar;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarDto {
    @NotNull(message = "id is required", groups = {OnUpdateCar.class})
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "model is required", groups = {OnUpdateCar.class, OnCreateCar.class})
    @Size(min = 2, max = 255, message = "model must must be of 2 - 255 characters",
            groups = {OnUpdateCar.class, OnCreateCar.class})
    private String model;

    @NotBlank(message = "producer is required",  groups = {OnUpdateCar.class, OnCreateCar.class})
    @Size(min = 3, max = 255, message = "producer must must be of 3 - 255 characters",
            groups = {OnUpdateCar.class, OnCreateCar.class})
    private String producer;

    @NotBlank(message = "code is required", groups = {OnUpdateCar.class, OnCreateCar.class})
    @Size(min = 3, max = 255, message = "code must be of 3 -  255 characters",
            groups = {OnUpdateCar.class, OnCreateCar.class})
    private String code;
}
