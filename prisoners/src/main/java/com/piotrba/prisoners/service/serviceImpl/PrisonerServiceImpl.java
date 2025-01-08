package com.piotrba.prisoners.service.serviceImpl;

import com.piotrba.prisoners.entity.Prisoner;
import com.piotrba.prisoners.repo.PrisonersRepository;
import com.piotrba.prisoners.service.PrisonerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PrisonerServiceImpl implements PrisonerService {

    private static final Logger logger = LoggerFactory.getLogger(PrisonerServiceImpl.class);

    private final PrisonersRepository prisonersRepository;

    @Override
    public List<Prisoner> findAll() {
        logger.info("Fetching all prisoners");
        return prisonersRepository.findAll();
    }

    @Override
    public Optional<Prisoner> findById(Long id) {
        logger.info("Fetching prisoner by ID: {}", id);
        return prisonersRepository.findById(id);
    }

    @Override
    @Transactional
    public Prisoner updatePrisoner(Long id, Prisoner newPrisoner) {
        logger.info("Updating prisoner with ID: {}", id);
        Optional<Prisoner> existingPrisonerOptional = prisonersRepository.findById(id);
        if (existingPrisonerOptional.isEmpty()) {
            logger.error("Prisoner with ID {} does not exist", id);
            throw new IllegalArgumentException("Prisoner does not exist");
        }
        Prisoner existingPrisoner = existingPrisonerOptional.get();
        existingPrisoner.setFirstName(newPrisoner.getFirstName());
        existingPrisoner.setLastName(newPrisoner.getLastName());
        existingPrisoner.setIncarcerationDate(newPrisoner.getIncarcerationDate());
        existingPrisoner.setImprisonmentEndDate(newPrisoner.getImprisonmentEndDate());
        existingPrisoner.setImprisonmentRigour(newPrisoner.getImprisonmentRigour());
        existingPrisoner.setAddress(newPrisoner.getAddress());
        logger.info("Prisoner updated successfully: {}", existingPrisoner);
        return existingPrisoner;
    }

    @Override
    @Transactional
    public void deletePrisoner(Long id) {
        logger.info("Deleting prisoner with ID: {}", id);
        prisonersRepository.deleteById(id);
        logger.info("Prisoner with ID {} deleted successfully", id);
    }

    @Override
    @Transactional
    public Prisoner addPrisoner(Prisoner prisoner) {
        logger.info("Adding new prisoner: {}", prisoner);
        Prisoner savedPrisoner = prisonersRepository.save(prisoner);
        logger.info("Prisoner added successfully: {}", savedPrisoner);
        return savedPrisoner;
    }
}
