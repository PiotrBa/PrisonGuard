package com.piotrba.guards.service.impl;

import com.piotrba.guards.client.PrisonerClient;
import com.piotrba.guards.dto.PrisonerDTO;
import com.piotrba.guards.service.PrisonerServiceDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PrisonerServiceImplDTO implements PrisonerServiceDTO {

    private final PrisonerClient prisonerClient;
    @Override
    public List<PrisonerDTO> findAllPrisoners() {
        return prisonerClient.getAllPrisoners();
    }

    @Override
    public PrisonerDTO findById(Long id) {
        return prisonerClient.getPrisonerById(id);
    }

    @Override
    public PrisonerDTO addPrisoner(PrisonerDTO prisonerDTO) {
        return prisonerClient.addPrisoner(prisonerDTO);
    }

    @Override
    public PrisonerDTO updatePrisoner(Long id, PrisonerDTO prisonerDTO) {
        return prisonerClient.updatePrisoner(id, prisonerDTO);
    }

    @Override
    public PrisonerDTO deletePrisoner(Long id) {
        return prisonerClient.deletePrisoner(id);
    }
}
