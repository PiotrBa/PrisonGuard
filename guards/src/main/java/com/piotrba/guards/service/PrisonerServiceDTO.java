package com.piotrba.guards.service;

import com.piotrba.guards.dto.PrisonerDTO;

import java.util.List;

public interface PrisonerServiceDTO {

    List<PrisonerDTO> findAllPrisoners();
    PrisonerDTO findById(Long id);
    PrisonerDTO addPrisoner(PrisonerDTO prisonerDTO);
    PrisonerDTO updatePrisoner(Long id, PrisonerDTO prisonerDTO);
    PrisonerDTO deletePrisoner(Long id);

}
