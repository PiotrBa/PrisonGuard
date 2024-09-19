package com.piotrba.guards.service.impl;

import com.piotrba.guards.entity.Guard;
import com.piotrba.guards.repo.GuardsRepository;
import com.piotrba.guards.service.GuardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
    @Transactional
    public Guard updateGuard(Long id, Guard newGuard) {
        Optional<Guard> existingGuardOptional = guardsRepository.findById(id);
        if (existingGuardOptional.isEmpty()) {
            throw new IllegalArgumentException("Guard does not exist");
        }
        Guard existingGuard = existingGuardOptional.get();
        existingGuard.setFirstName(newGuard.getFirstName());
        existingGuard.setLastName(newGuard.getLastName());
        existingGuard.setEmail(newGuard.getEmail());
        existingGuard.setPhoneNumber(newGuard.getPhoneNumber());
        existingGuard.setAddress(newGuard.getAddress());
        existingGuard.setActive(newGuard.getActive());
        return existingGuard;
    }


}
