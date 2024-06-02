package br.com.imrochamatheus.super_parts.dto;

import br.com.imrochamatheus.super_parts.validation.OnCreatePart;
import br.com.imrochamatheus.super_parts.validation.OnUpdatePart;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PartDto {
    @NotNull(message = "id is is required", groups = {OnUpdatePart.class})
    private Long id;

    @NotBlank(message = "name is required", groups = {OnCreatePart.class, OnUpdatePart.class})
    @Size(min = 3, max = 255,
            message = "name must must be of 2 - 255 characters",
            groups = {OnCreatePart.class, OnUpdatePart.class}
    )
    private String name;

    @NotBlank(message = "description is required", groups = {OnCreatePart.class, OnUpdatePart.class})
    @Size(min = 3,
            message = "description must contain at least 3 characters",
            groups = {OnCreatePart.class, OnUpdatePart.class}
    )
    private String description;

    @NotBlank(message = "serial is required", groups = {OnCreatePart.class, OnUpdatePart.class})
    @Size(min = 3, max = 255,
            message = "serial must must be of 2 - 255 characters",
            groups = {OnCreatePart.class, OnUpdatePart.class}
    )
    private String serial;

    @NotBlank(message = "producer is required", groups = {OnCreatePart.class, OnUpdatePart.class})
    @Size(max = 255,
            message = "producer must contain a maximun of 3 characters",
            groups = {OnCreatePart.class, OnUpdatePart.class}
    )
    private String producer;

    @NotBlank(message = "carModel is required", groups = {OnCreatePart.class, OnUpdatePart.class})
    @Size(max = 255,
            message = "carModel must contain a maximun of 3 characters",
            groups = {OnCreatePart.class, OnUpdatePart.class}
    )
    private String carModel;

    @NotNull(message = "carId is required",
            groups = {OnCreatePart.class, OnUpdatePart.class}
    )
    private Long carId;
}
