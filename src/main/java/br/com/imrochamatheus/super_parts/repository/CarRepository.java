package br.com.imrochamatheus.super_parts.repository;

import br.com.imrochamatheus.super_parts.dto.TopKProducersDto;
import br.com.imrochamatheus.super_parts.dto.projection.CarProducerProjection;
import br.com.imrochamatheus.super_parts.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findFirstByModelOrCode(String model, String Code);

    @Query(value = "SELECT c.producer producer FROM Car c GROUP BY producer")
    List<CarProducerProjection> findAllProducers();

    Page<Car> findByModelIsContainingOrProducerIsContaining(String model, String producer, Pageable pageable);

    @Query(value = "SELECT new br.com.imrochamatheus.super_parts.dto.TopKProducersDto(c.producer, COUNT(c) quantity)"+
            " FROM Car c" +
            " GROUP BY producer" +
            " ORDER BY quantity DESC")
    List<TopKProducersDto> findTopKProducers();

}
