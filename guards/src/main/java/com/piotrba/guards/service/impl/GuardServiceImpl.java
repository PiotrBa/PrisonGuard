package com.piotrba.guards.service.impl;

import com.piotrba.guards.client.PrisonerClient;
import com.piotrba.guards.client.VisitorClient;
import com.piotrba.guards.dto.AssignRequest;
import com.piotrba.guards.dto.prisoner.PrisonerDTO;
import com.piotrba.guards.dto.visitor.RelationshipToPrisonerDTO;
import com.piotrba.guards.dto.visitor.VisitorDTO;
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
    private final PrisonerClient prisonerClient;
    private final VisitorClient visitorClient;

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

    @Override
    public PrisonerDTO getPrisonerById(Long id) {
        PrisonerDTO prisoner = prisonerClient.getPrisonerById(id);
        if (prisoner == null) {
            throw new IllegalArgumentException("Prisoner with ID " + id + " does not exist");
        }
        List<VisitorDTO> visitors = visitorClient.getVisitorsByPrisonerId(id);
        prisoner.setVisitors(visitors);
        return prisoner;
    }

    @Override
    public void assignPrisonerToVisitor(AssignRequest request) {
        PrisonerDTO prisoner = prisonerClient.getPrisonerById(request.getPrisonerId());
        if (prisoner == null) {
            throw new IllegalArgumentException("Prisoner with ID " + request.getPrisonerId() + " does not exist");
        }
        VisitorDTO visitor = visitorClient.getVisitorById(request.getVisitorId());
        if (visitor == null) {
            throw new IllegalArgumentException("Visitor with ID " + request.getVisitorId() + " does not exist");
        }
        visitor.setPrisonerIdNumber(request.getPrisonerId());
        visitor.setRelationshipToPrisoner(RelationshipToPrisonerDTO.valueOf(request.getRelationshipToPrisoner()));
        visitorClient.updateVisitor(request.getVisitorId(), visitor);
    }
}
