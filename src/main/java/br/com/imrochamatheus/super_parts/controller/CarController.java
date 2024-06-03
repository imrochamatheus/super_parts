package br.com.imrochamatheus.super_parts.controller;

import br.com.imrochamatheus.super_parts.dto.CarDto;
import br.com.imrochamatheus.super_parts.dto.TopKProducersDto;
import br.com.imrochamatheus.super_parts.dto.projection.CarProducerProjection;
import br.com.imrochamatheus.super_parts.validation.OnUpdateCar;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import br.com.imrochamatheus.super_parts.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping(value = "/topKProducers")
    public ResponseEntity<List<TopKProducersDto>> findTopKProducers () {
        List<TopKProducersDto> producersList = this.carService.findTopKProducers();
        return ResponseEntity.ok(producersList);
    }

    @GetMapping(value = "/producers")
    public ResponseEntity<List<CarProducerProjection>> findAllProducers () {
        List<CarProducerProjection> producersList = this.carService.findAllProducers();
        return ResponseEntity.ok(producersList);
    }

    @GetMapping()
    public ResponseEntity<List<CarDto>> findAll () {
        List<CarDto> response = this.carService.findAllCars();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CarDto> findById (@PathVariable Long id) {
        CarDto response = this.carService.findCarById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<CarDto>> findAllPaged(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CarDto> response = this.carService.findAllCarsPaged(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paged/{therm}")
    public ResponseEntity<Page<CarDto>> findAllPaged(
            @PathVariable String therm,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<CarDto> response = this.carService.findAllCarsPagedByTherm(therm, page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CarDto> saveCar (@RequestBody @Valid CarDto carRequest) {
        CarDto newCar = this.carService.saveCar(carRequest);

        return ResponseEntity.ok(newCar);
    }

    @PutMapping()
    public ResponseEntity<CarDto> updateCar (
            @RequestBody @Validated({OnUpdateCar.class}) CarDto carRequest
    ) {
        CarDto updatedCar = this.carService.updateCar(carRequest);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCar (@PathVariable long id) {
        this.carService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
