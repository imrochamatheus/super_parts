package br.com.imrochamatheus.super_parts.service;

import br.com.imrochamatheus.super_parts.dto.CarDto;
import br.com.imrochamatheus.super_parts.dto.TopKProducersDto;
import br.com.imrochamatheus.super_parts.exceptions.CarAlreadyExistsException;
import br.com.imrochamatheus.super_parts.exceptions.CarNotFoundException;
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

    public List<TopKProducersDto> findTopKProducers () {
        return this.carRepository.findTopKProducers();
    }

    public List<CarDto> findAll () {
        return this.carRepository.findAll()
                .stream().map(this.carMapper::fromCar).toList();
    }

    public CarDto findById (Long id) {
        Car car =  this.carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car with id " + id + " does not exists"));

        return this.carMapper.fromCar(car);
    }

    public CarDto saveCar (CarDto carRequest) {
        Optional<Car> carExists = this.carRepository
                .findFirstByModelOrCode(carRequest.getModel(), carRequest.getCode());

        if(carExists.isPresent()) {
            throw new CarAlreadyExistsException("There is already a car with specified code or model");
        }

        Car newCar = this.carRepository.save(this.carMapper.toCar(carRequest));
        return this.carMapper.fromCar(newCar);
    }

    public CarDto updateCar(CarDto carRequest) {
        Car carToUpdate = this.carRepository
                .findById(carRequest.getId())
                .orElseThrow(() -> new CarNotFoundException("Car with id " + carRequest.getId() + " does not exists"));

        Car carAlreadyExists =  this.carRepository
                .findFirstByModelOrCode(carRequest.getModel(), carRequest.getCode()).orElse(null);

        if(carAlreadyExists != null && carAlreadyExists.getId() != carToUpdate.getId()) {
            throw new CarAlreadyExistsException("There is already a car with specified code or model");
        }

        Car updatedCar = this.carRepository.save(this.carMapper.toCar(carRequest));
        return this.carMapper.fromCar(updatedCar);
    }
}
