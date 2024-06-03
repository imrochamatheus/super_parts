package br.com.imrochamatheus.super_parts.service;

import br.com.imrochamatheus.super_parts.dto.PartDto;
import br.com.imrochamatheus.super_parts.dto.TopKCarsMostPartsDto;
import br.com.imrochamatheus.super_parts.exceptions.PartAlreadyExistsException;
import br.com.imrochamatheus.super_parts.exceptions.PartNotFoundException;
import br.com.imrochamatheus.super_parts.model.Part;
import br.com.imrochamatheus.super_parts.model.mapper.PartMapper;
import br.com.imrochamatheus.super_parts.repository.PartRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartMapper partMapper;

    public List<PartDto> findAllParts() {
        return this.partRepository
                .findAll()
                .stream()
                .map(this.partMapper::fromModel)
                .toList();
    }

    public Page<PartDto> findAllPartsPaged (int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.partRepository
                .findAll(pageable)
                .map(this.partMapper::fromModel);
    }

    public Page<PartDto> findAllPartsPagedByTherm(String therm, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return  this.partRepository
                .findByNameIsContainingOrSerialIsContaining(therm, therm, pageable)
                .map(this.partMapper::fromModel);
    }

    public PartDto findPartById (Long id) {
        Part part = this.partRepository.findById(id)
                .orElseThrow(() -> new PartNotFoundException("Part with id " + id + " does not exists"));
        return this.partMapper.fromModel(part);
    }

    public List<PartDto> findPartByCarId (Long id) {
        return this.partRepository
                .findByCarId(id)
                .stream()
                .map(this.partMapper::fromModel).toList();
    }

    public List<TopKCarsMostPartsDto> findTopKCarsMostParts () {
        return this.partRepository.findTopKCarsMostParts();
    }

    public PartDto savePart(PartDto requestPartDto) {
        Optional<Part> partExists = this.partRepository
            .findByNameOrSerial(requestPartDto.getName(), requestPartDto.getSerial());

        if(partExists.isPresent()) {
            throw new PartAlreadyExistsException("There is already a part with specified name or serial");
        }

        Part newPart = this.partRepository.save(this.partMapper.toModel(requestPartDto));
        return this.partMapper.fromModel(newPart);
    }

    public PartDto updatePart (PartDto partRequestDto) {
        Part part =
            this.partRepository
                .findById(partRequestDto.getId())
                .orElseThrow(() -> new PartNotFoundException("Part with id " + partRequestDto.getId() + " does not exists"));

        Part partAlreadyExists = this.partRepository
                .findByNameOrSerial(partRequestDto.getName(), partRequestDto.getSerial())
                .orElse(null);

        if(partAlreadyExists == null || partAlreadyExists.getId() != part.getId()) {
            throw new PartAlreadyExistsException("There is already a part with specified name or serial");
        }

        Part newPart = this.partRepository.save(this.partMapper.toModel(partRequestDto));
        return this.partMapper.fromModel(newPart);
    }

    public void deletePart (Long id) {
        Optional<Part> partExists = this.partRepository.findById(id);

        if(partExists.isEmpty()) {
            throw new PartNotFoundException("Part with id " + id + " does not exists");
        }
        this.partRepository.deleteById(id);
    }
}
