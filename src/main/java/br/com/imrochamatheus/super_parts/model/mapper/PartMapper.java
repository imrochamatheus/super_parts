package br.com.imrochamatheus.super_parts.model.mapper;

import br.com.imrochamatheus.super_parts.dto.PartDto;
import br.com.imrochamatheus.super_parts.model.Part;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PartDto fromModel (Part part) {
        return this.modelMapper.map(part, PartDto.class);
    }

    public Part toModel (PartDto partDto) {
        return this.modelMapper.map(partDto, Part.class);
    }
}
