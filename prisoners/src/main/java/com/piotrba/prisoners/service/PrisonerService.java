package com.piotrba.prisoners.service;

import com.piotrba.prisoners.entity.Prisoner;

import java.util.List;
import java.util.Optional;

public interface PrisonerService {

    List<Prisoner> findAll();
    Optional<Prisoner> findById(Long id);
    Optional<Prisoner> updatePrisoner (Long id, Prisoner prisoner);
}
