package br.com.imrochamatheus.super_parts.controller;

import br.com.imrochamatheus.super_parts.dto.PartDto;
import br.com.imrochamatheus.super_parts.service.PartService;
import br.com.imrochamatheus.super_parts.validation.OnCreatePart;
import br.com.imrochamatheus.super_parts.validation.OnUpdatePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping
    public ResponseEntity<PartDto> savePart (
            @RequestBody @Validated({OnCreatePart.class}) PartDto requestPart)
    {
        PartDto partDto = this.partService.savePart(requestPart);
        return ResponseEntity.status(HttpStatus.CREATED).body(partDto);
    }

    @PutMapping
    public ResponseEntity<PartDto> updatePart(
            @RequestBody @Validated({OnUpdatePart.class}) PartDto requestPart
    ) {
        PartDto partDto = this.partService.updatePart(requestPart);
        return ResponseEntity.ok(partDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePartById(@PathVariable long id) {
        this.partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }
}
