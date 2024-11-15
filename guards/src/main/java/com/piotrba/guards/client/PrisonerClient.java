package com.piotrba.guards.client;

import com.piotrba.guards.dto.PrisonerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "prisoner-client", url = "http://localhost:8081/prisoner")
public interface PrisonerClient {

    @GetMapping
    List<PrisonerDTO> getAllPrisoners();

    @GetMapping("/{id}")
    PrisonerDTO getPrisonerById(@PathVariable Long id);

    @PostMapping("/add")
    PrisonerDTO addPrisoner(@RequestBody PrisonerDTO prisonerDTO);

    @PostMapping("/update?{id}")
    PrisonerDTO updatePrisoner(@PathVariable Long id, @RequestBody PrisonerDTO prisonerDTO);

    @PostMapping("/delete/{id}")
    PrisonerDTO deletePrisoner(@PathVariable Long id);
}
