package br.com.imrochamatheus.super_parts.repository;

import br.com.imrochamatheus.super_parts.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByModelOrCode(String model, String Code);
}
