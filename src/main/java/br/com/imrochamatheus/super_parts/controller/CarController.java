package br.com.imrochamatheus.super_parts.controller;

import br.com.imrochamatheus.super_parts.dto.CarDto;
import br.com.imrochamatheus.super_parts.dto.TopKProducersDto;
import br.com.imrochamatheus.super_parts.validation.OnUpdateCar;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok().body(producersList);
    }

    @GetMapping()
    public ResponseEntity<List<CarDto>> getAll () {
        List<CarDto> response = this.carService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CarDto> findById (@PathVariable Long id) {
        CarDto response = this.carService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CarDto> saveCar (@RequestBody @Valid CarDto carRequest) {
        CarDto newCar = this.carService.saveCar(carRequest);

        return ResponseEntity.ok().body(newCar);
    }

    @PutMapping()
    public ResponseEntity<CarDto> updateCar (
            @RequestBody @Validated({OnUpdateCar.class}) CarDto carRequest
    ) {
        CarDto updatedCar = this.carService.updateCar(carRequest);
        return ResponseEntity.ok(updatedCar);
    }
}
