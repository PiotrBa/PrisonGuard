package com.piotrba.guards.controller;

import com.piotrba.guards.client.PrisonerClient;
import com.piotrba.guards.dto.prisoner.PrisonerDTO;
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

    private final PrisonerClient prisonerClient;

    @GetMapping("/all")
    public List<PrisonerDTO> getAllPrisoners() {
        logger.info("Received request to get all prisoners");
        return prisonerClient.getAllPrisoners();
    }

    @GetMapping("/{id}")
    public PrisonerDTO getPrisonerById(@PathVariable Long id) {
        logger.info("Received request to get prisoner by id: {}", id);
        return prisonerClient.getPrisonerById(id);
    }

    @PostMapping("/add")
    public PrisonerDTO addPrisoner(@RequestBody PrisonerDTO prisonerDTO) {
        logger.info("Received request to add a new prisoner: {}", prisonerDTO);
        return prisonerClient.addPrisoner(prisonerDTO);
    }

    @PostMapping("/update/{id}")
    public PrisonerDTO updatePrisoner(@PathVariable Long id, @RequestBody PrisonerDTO prisonerDTO) {
        logger.info("Received request to update prisoner with id: {}", id);
        return prisonerClient.updatePrisoner(id, prisonerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePrisoner(@PathVariable Long id) {
        logger.info("Received request to delete prisoner with id: {}", id);
        prisonerClient.deletePrisoner(id);
    }
}
