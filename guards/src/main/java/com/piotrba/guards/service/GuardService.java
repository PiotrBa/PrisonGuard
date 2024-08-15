package com.piotrba.guards.service;

import com.piotrba.guards.entity.Guard;

import java.util.Optional;

public interface GuardService {

    Optional<Guard> findGuardById(Long id);
}
