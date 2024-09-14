package com.piotrba.guards.controller;

import com.piotrba.guards.entity.Guard;
import com.piotrba.guards.service.GuardService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guard")
@AllArgsConstructor
public class GuardController {

    private static final Logger logger = LoggerFactory.getLogger(GuardController.class);

    private final GuardService guardsService;

    @GetMapping
    public ResponseEntity<List<Guard>> getAllGuards() {
        logger.info("Received request to get all guards");

        List<Guard> guards = guardsService.findAllGuards();
        if (guards.isEmpty()) {
            logger.warn("No guards found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            logger.info("Returning {} guards", guards.size());
            return ResponseEntity.ok(guards);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guard> getGuardById(@PathVariable Long id){
        logger.info("Received request to get guard by id: {}", id);

        Optional<Guard> optionalGuard = guardsService.findGuardById(id);
        if (optionalGuard.isPresent()) {
            logger.info("Guard with id: {} found", id);
            return ResponseEntity.ok(optionalGuard.get());
        } else {
            logger.warn("Guard with id: {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Guard> getNewGuard(@RequestBody Guard guard){
        logger.info("Received request to register a new guard");

        try {
            Guard registeredGuard = guardsService.registerNewGuard(guard);
            logger.info("Guard registered with id: {}", registeredGuard.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredGuard);
        } catch (IllegalStateException e) {
            logger.error("Conflict while registering guard: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Guard> updateGuard(@PathVariable Long id, @RequestBody Guard guard){
        logger.info("Received request to update guard with id: {}", id);

        try {
            guard.setId(id);
            Guard updatedGuard = guardsService.updateGuard(guard);
            logger.info("Guard with id: {} updated successfully", id);
            return ResponseEntity.ok(updatedGuard);
        } catch (IllegalStateException e) {
            logger.error("Guard with id: {} not found for update", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
