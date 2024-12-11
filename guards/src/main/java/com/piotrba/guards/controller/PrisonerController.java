package com.piotrba.guards.controller;

import com.piotrba.guards.client.PrisonerClient;
import com.piotrba.guards.dto.prisoner.PrisonerDTO;
import com.piotrba.guards.service.GuardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guard/prisoner")
@AllArgsConstructor
public class PrisonerController {

    private final GuardService guardService;
    private final PrisonerClient prisonerClient;
    @GetMapping("/all")
    public List<PrisonerDTO> getAllPrisoners() {
        return prisonerClient.getAllPrisoners();
    }

    @GetMapping("/{id}")
    public PrisonerDTO getPrisonerById(@PathVariable Long id) {
        return guardService.getPrisonerById(id);
    }
    @PostMapping("/add")
    public PrisonerDTO addPrisoner(@RequestBody PrisonerDTO prisonerDTO) {
        return prisonerClient.addPrisoner(prisonerDTO);
    }

    @PostMapping("/update/{id}")
    public PrisonerDTO updatePrisoner(@PathVariable Long id, @RequestBody PrisonerDTO prisonerDTO) {
        return prisonerClient.updatePrisoner(id, prisonerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePrisoner(@PathVariable Long id) {
        prisonerClient.deletePrisoner(id);
    }
}
