package com.piotrba.prisoners.controller;

import com.piotrba.prisoners.entity.Prisoner;
import com.piotrba.prisoners.service.PrisonerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prisoner")
@AllArgsConstructor
public class PrisonerController {

    private static final Logger logger = LoggerFactory.getLogger(PrisonerController.class);

    private final PrisonerService prisonerService;

    @GetMapping()
    public ResponseEntity<List<Prisoner>> getAllPrisoners() {
        logger.info("Received request to get all prisoners");

        List<Prisoner> prisoners = prisonerService.findAll();
        if (prisoners.isEmpty()) {
            logger.warn("No prisoners found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            logger.info("Returning {} prisoners", prisoners.size());
            return ResponseEntity.ok(prisoners);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prisoner> getPrisonerById(@PathVariable Long id) {
        logger.info("Received request to get prisoner by id: {}", id);

        Optional<Prisoner> prisonerOptional = prisonerService.findById(id);
        if (prisonerOptional.isPresent()) {
            logger.info("Prisoner with id: {} found", id);
            return ResponseEntity.ok(prisonerOptional.get());
        } else {
            logger.warn("Prisoner with id: {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Prisoner> updatePrisoner(@PathVariable Long id, @RequestBody Prisoner prisoner) {
        logger.info("Received request to update prisoner with id: {}", id);

        Optional<Prisoner> prisonerOptional = prisonerService.updatePrisoner(id, prisoner);
        if (prisonerOptional.isPresent()) {
            logger.info("Prisoner with id: {} updated successfully", id);
            return ResponseEntity.ok(prisonerOptional.get());
        } else {
            logger.error("Prisoner with id: {} not found for update", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
