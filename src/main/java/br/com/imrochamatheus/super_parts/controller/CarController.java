package br.com.imrochamatheus.super_parts.controller;

import br.com.imrochamatheus.super_parts.dto.CarDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.imrochamatheus.super_parts.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CarDto> saveCar (@RequestBody @Valid CarDto carRequest) {
        CarDto newCar = this.carService.saveCar(carRequest);

        return ResponseEntity.ok().body(newCar);
    }
}
