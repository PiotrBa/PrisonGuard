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

    @Override
    public Guard registerNewGuard(Guard guard) {
        Optional<Guard> existingGuard = guardsRepository.findByEmail(guard.getEmail());
        if (existingGuard.isPresent()){
            throw new IllegalStateException("Guard already exists with this email address");
        }
        guard.setGrantHighLevelAccess(false);
        guard.setActive(true);
        return guardsRepository.save(guard);
    }

    @Override
    public Guard updateGuard(Guard guard) {
        Optional<Guard> findExistingGuard = guardsRepository.findById(guard.getId());
        if (findExistingGuard.isPresent()){
            return guardsRepository.save(guard);
        }else {
            throw new IllegalStateException("Guard does not exists");
        }
    }
}
