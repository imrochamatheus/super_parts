package br.com.imrochamatheus.super_parts.model.mapper;

import br.com.imrochamatheus.super_parts.dto.CarDto;
import br.com.imrochamatheus.super_parts.model.Car;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CarDto fromCar (Car car) {
        return this.modelMapper.map(car, CarDto.class);
    }

    public Car toCar (CarDto dto) {
        return this.modelMapper.map(dto, Car.class);
    }
}
