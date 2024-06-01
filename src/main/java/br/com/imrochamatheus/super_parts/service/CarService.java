package br.com.imrochamatheus.super_parts.service;

import br.com.imrochamatheus.super_parts.dto.CarDto;
import br.com.imrochamatheus.super_parts.exceptions.CarAlreadyExistsException;
import br.com.imrochamatheus.super_parts.mapper.CarMapper;
import lombok.NoArgsConstructor;
import br.com.imrochamatheus.super_parts.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.imrochamatheus.super_parts.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    public List<CarDto> findAll () {
        return this.carRepository.findAll()
                .stream().map(this.carMapper::fromCar).toList();
    }

    public CarDto saveCar (CarDto carRequest) {
        Optional<Car> carExists = this.carRepository.findByModelOrCode(
                carRequest.getModel(),
                carRequest.getCode()
        );

        if(carExists.isPresent()) {
            throw new CarAlreadyExistsException("There is already a car with specified code or model");
        }

        Car newCar = this.carMapper.toCar(carRequest);
        Car createdCar = this.carRepository.save(newCar);

        return this.carMapper.fromCar(createdCar);
    }
}
