package br.com.imrochamatheus.super_parts.controller;

import br.com.imrochamatheus.super_parts.dto.CarDto;
import br.com.imrochamatheus.super_parts.dto.TopKProducersDto;
import br.com.imrochamatheus.super_parts.dto.projection.CarProducerProjection;
import br.com.imrochamatheus.super_parts.utils.ErrorResponse;
import br.com.imrochamatheus.super_parts.validation.OnCreateCar;
import br.com.imrochamatheus.super_parts.validation.OnUpdateCar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import br.com.imrochamatheus.super_parts.service.CarService;

import java.util.List;

@Tag(name = "Cars", description = "Handling cars")
@RestController
@RequestMapping(value = "/car", produces = "application/json")
public class CarController {
    @Autowired
    CarService carService;

    @Operation(summary = "Get top 10 producers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", content = {
            @Content(schema = @Schema(implementation = TopKProducersDto.class))
        })
    })
    @GetMapping(value = "/topKProducers")
    public ResponseEntity<List<TopKProducersDto>> findTopKProducers () {
        List<TopKProducersDto> producersList = this.carService.findTopKProducers();
        return ResponseEntity.ok(producersList);
    }

    @Operation(summary = "Get all producers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", content = {
            @Content(schema = @Schema(implementation = CarProducerProjection.class))
        })
    })
    @GetMapping(value = "/producers")
    public ResponseEntity<List<CarProducerProjection>> findAllProducers () {
        List<CarProducerProjection> producersList = this.carService.findAllProducers();
        return ResponseEntity.ok(producersList);
    }

    @Operation(summary = "Get all cars")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(schema = @Schema(implementation = CarDto.class)))
    })
    @GetMapping()
    public ResponseEntity<List<CarDto>> findAll () {
        List<CarDto> response = this.carService.findAllCars();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find car by ID", description = "Car must exist")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(schema = @Schema(implementation = CarDto.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(path = "{id}")
    public ResponseEntity<CarDto> findById (@PathVariable Long id) {
        CarDto response = this.carService.findCarById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all cars", description = "List all cars paged")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(schema = @Schema(implementation = Page.class))            )
    })
    @GetMapping("/paged")
    public ResponseEntity<Page<CarDto>> findAllPaged(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Page<CarDto> response = this.carService.findAllCarsPaged(page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find cars by therm", description = "List cars paged by therm")
    @ApiResponses(
        @ApiResponse(responseCode = "200", description = "Ok", content =
        @Content(schema = @Schema(implementation = Page.class))            )
    )
    @GetMapping("/paged/{therm}")
    public ResponseEntity<Page<CarDto>> findAllPaged(
        @PathVariable String therm,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        Page<CarDto> response = this.carService.findAllCarsPagedByTherm(therm, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create car", description = "Create a new car", requestBody =
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content =  @Content(schema = @Schema(implementation = CarDto.class)),
            required = true
    ))
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Created",
            content = @Content(schema = @Schema(implementation = CarDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Content is not valid",
            content = @Content(schema = @Schema(implementation = CarDto.class)))
    })
    @PostMapping()
    public ResponseEntity<CarDto> saveCar (@RequestBody @Validated({OnCreateCar.class}) CarDto carRequest) {
        CarDto newCar = this.carService.saveCar(carRequest);
        return ResponseEntity.ok(newCar);
    }

    @Operation(summary = "Update car", description = "Update a car", requestBody =
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = CarDto.class)),
            required = true
    ))
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Updated",
            content = @Content(schema = @Schema(implementation = CarDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid body params",
            content = @Content(schema = @Schema(implementation = CarDto.class))
        )
    })
    @PutMapping()
    public ResponseEntity<CarDto> updateCar (
            @RequestBody @Validated({OnUpdateCar.class}) CarDto carRequest
    ) {
        CarDto updatedCar = this.carService.updateCar(carRequest);
        return ResponseEntity.ok(updatedCar);
    }

    @Operation(summary = "Delete car", description = "Deleted")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "No content", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCar (@PathVariable long id) {
        this.carService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
