package com.digitalidentityapi.citizen.controller;

import com.digitalidentityapi.citizen.dto.OfferedSolutionDto;
import com.digitalidentityapi.citizen.service.IOfferedSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offered-solutions")
public class OfferedSolutionController {

    private final IOfferedSolutionService offeredSolutionService;

    @Autowired
    public OfferedSolutionController(IOfferedSolutionService offeredSolutionService) {
        this.offeredSolutionService = offeredSolutionService;
    }

    @PostMapping
    public ResponseEntity<OfferedSolutionDto> createOfferedSolution(@RequestBody OfferedSolutionDto offeredSolutionDto) {
        OfferedSolutionDto created = offeredSolutionService.createOfferedSolution(offeredSolutionDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferedSolutionDto> getOfferedSolutionById(@PathVariable int id) {
        OfferedSolutionDto dto = offeredSolutionService.getOfferedSolutionById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<OfferedSolutionDto>> getAllOfferedSolutions() {
        List<OfferedSolutionDto> dtos = offeredSolutionService.getAllOfferedSolutions();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferedSolutionDto> updateOfferedSolution(@PathVariable int id, @RequestBody OfferedSolutionDto offeredSolutionDto) {
        OfferedSolutionDto updated = offeredSolutionService.updateOfferedSolution(id, offeredSolutionDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOfferedSolution(@PathVariable int id) {
        offeredSolutionService.deleteOfferedSolution(id);
        return ResponseEntity.noContent().build();
    }
}
