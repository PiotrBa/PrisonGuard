package com.piotrba.prisoners.service.serviceImpl;

import com.piotrba.prisoners.entity.Prisoner;
import com.piotrba.prisoners.repo.PrisonersRepository;
import com.piotrba.prisoners.service.PrisonerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PrisonerServiceImpl implements PrisonerService {

    private final PrisonersRepository prisonersRepository;
    @Override
    public List<Prisoner> findAll() {
        return prisonersRepository.findAll();
    }

    @Override
    public Optional<Prisoner> findById(Long id) {
        return prisonersRepository.findById(id);
    }

    @Override
    @Transactional
    public Prisoner updatePrisoner(Long id, Prisoner newPrisoner) {
        Optional<Prisoner> existingPrisonerOptional = prisonersRepository.findById(id);
        if (existingPrisonerOptional.isEmpty()){
            throw new IllegalArgumentException("Prisoner does not exist");
        }
        Prisoner existingPrisoner = existingPrisonerOptional.get();
        existingPrisoner.setFirstName(newPrisoner.getFirstName());
        existingPrisoner.setLastName(newPrisoner.getLastName());
        existingPrisoner.setIncarcerationDate(newPrisoner.getIncarcerationDate());
        existingPrisoner.setImprisonmentEndTime(newPrisoner.getImprisonmentEndTime());
        existingPrisoner.setImprisonmentRigour(newPrisoner.getImprisonmentRigour());
        existingPrisoner.setAddress(newPrisoner.getAddress());
        return existingPrisoner;
    }

    @Override
    @Transactional
    public void deletePrisoner(Long id) {
        prisonersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Prisoner addPrisoner(Prisoner prisoner) {
        return prisonersRepository.save(prisoner);
    }
}
