package br.com.imrochamatheus.super_parts.controller;

import br.com.imrochamatheus.super_parts.dto.PartDto;
import br.com.imrochamatheus.super_parts.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/part")
public class PartController {

    @Autowired
    private PartService partService;

    @GetMapping
    public ResponseEntity<List<PartDto>> findAllParts() {
        List<PartDto> responseList = this.partService.findAllParts();
        return ResponseEntity.ok(responseList);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PartDto> findPartById(@PathVariable long id) {
        PartDto partDto = this.partService.findPartById(id);
        return ResponseEntity.ok(partDto);
    }

}
