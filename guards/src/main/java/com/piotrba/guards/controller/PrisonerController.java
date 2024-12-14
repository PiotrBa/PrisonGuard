package com.piotrba.guards.controller;

import com.piotrba.guards.client.PrisonerClient;
import com.piotrba.guards.dto.prisoner.PrisonerDTO;
import com.piotrba.guards.service.GuardService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guard/prisoner")
@AllArgsConstructor
public class PrisonerController {

    private static final Logger logger = LoggerFactory.getLogger(PrisonerController.class);

    private final GuardService guardService;
    private final PrisonerClient prisonerClient;

    @GetMapping("/all")
    public List<PrisonerDTO> getAllPrisoners() {
        logger.info("Received request to get all prisoners");
        List<PrisonerDTO> prisoners = prisonerClient.getAllPrisoners();
        logger.info("Returning {} prisoners", prisoners.size());
        return prisoners;
    }

    @GetMapping("/{id}")
    public PrisonerDTO getPrisonerById(@PathVariable Long id) {
        logger.info("Received request to get prisoner with id: {}", id);
        PrisonerDTO prisoner = guardService.getPrisonerById(id);
        if (prisoner == null) {
            logger.warn("Prisoner with id: {} not found", id);
        } else {
            logger.info("Returning prisoner: {}", prisoner);
        }
        return prisoner;
    }

    @PostMapping("/add")
    public PrisonerDTO addPrisoner(@RequestBody PrisonerDTO prisonerDTO) {
        logger.info("Received request to add a new prisoner: {}", prisonerDTO);
        PrisonerDTO savedPrisoner = prisonerClient.addPrisoner(prisonerDTO);
        logger.info("Prisoner added with id: {}", savedPrisoner.getId());
        return savedPrisoner;
    }

    @PostMapping("/update/{id}")
    public PrisonerDTO updatePrisoner(@PathVariable Long id, @RequestBody PrisonerDTO prisonerDTO) {
        logger.info("Received request to update prisoner with id: {}", id);
        PrisonerDTO updatedPrisoner = prisonerClient.updatePrisoner(id, prisonerDTO);
        logger.info("Prisoner with id: {} updated successfully", id);
        return updatedPrisoner;
    }

    @DeleteMapping("/delete/{id}")
    public void deletePrisoner(@PathVariable Long id) {
        logger.info("Received request to delete prisoner with id: {}", id);
        prisonerClient.deletePrisoner(id);
        logger.info("Prisoner with id: {} deleted successfully", id);
    }
}
