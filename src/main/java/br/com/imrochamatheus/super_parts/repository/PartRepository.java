package br.com.imrochamatheus.super_parts.repository;

import br.com.imrochamatheus.super_parts.dto.TopKCarsMostPartsDto;
import br.com.imrochamatheus.super_parts.dto.TopKProducersDto;
import br.com.imrochamatheus.super_parts.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findByNameOrSerial(String name, String serial);

    Page<Part> findByNameIsContainingOrSerialIsContaining(String name, String serial, Pageable pageable);

    @Query(value = "SELECT new br.com.imrochamatheus.super_parts.dto.TopKCarsMostPartsDto(p.carModel, COUNT(p) partsQuantity)" +
            " FROM Part p" +
            " GROUP BY carModel" +
            " ORDER BY partsQuantity DESC" +
            " LIMIT 10")
    List<TopKCarsMostPartsDto> findTopKCarsMostParts();
}
