package com.piotrba.guards.service;

import com.piotrba.guards.entity.Guard;

import java.util.List;
import java.util.Optional;

public interface GuardService {

    List<Guard> findAllGuards();
    Optional<Guard> findGuardById(Long id);
}
