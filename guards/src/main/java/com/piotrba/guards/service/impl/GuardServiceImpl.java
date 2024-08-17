package com.piotrba.guards.service.impl;

import com.piotrba.guards.entity.Guard;
import com.piotrba.guards.repo.GuardsRepository;
import com.piotrba.guards.service.GuardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuardServiceImpl implements GuardService {

    private final GuardsRepository guardsRepository;

    @Override
    public List<Guard> findAllGuards() {
        return guardsRepository.findAll();
    }

    @Override
    public Optional<Guard> findGuardById(Long id) {
        return guardsRepository.findById(id);
    }
}
