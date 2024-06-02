package br.com.imrochamatheus.super_parts.service;

import br.com.imrochamatheus.super_parts.dto.PartDto;
import br.com.imrochamatheus.super_parts.exceptions.PartNotFoundException;
import br.com.imrochamatheus.super_parts.model.Part;
import br.com.imrochamatheus.super_parts.model.mapper.PartMapper;
import br.com.imrochamatheus.super_parts.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public PartDto findPartById (Long id) {
        Part part = this.partRepository.findById(id)
                .orElseThrow(() -> new PartNotFoundException("Part with id " + id + " does not exists"));
        return this.partMapper.fromModel(part);
    }
}
