package com.piotrba.prisoners.service.serviceImpl;

import com.piotrba.prisoners.entity.Prisoner;
import com.piotrba.prisoners.repo.PrisonersRepository;
import com.piotrba.prisoners.service.PrisonerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Optional<Prisoner> updatePrisoner(Long id, Prisoner newPrisoner) {
        Optional<Prisoner> prisonerOptional = prisonersRepository.findById(id);
        if (prisonerOptional.isPresent()) {
            Prisoner prisonerToUpdate = prisonerOptional.get();
            prisonerToUpdate.setFirstName(newPrisoner.getFirstName());
            prisonerToUpdate.setLastName(newPrisoner.getLastName());
            prisonerToUpdate.setIncarcerationDate(newPrisoner.getIncarcerationDate());
            prisonerToUpdate.setImprisonmentEndTime(newPrisoner.getImprisonmentEndTime());
            prisonerToUpdate.setImprisonmentRigour(newPrisoner.getImprisonmentRigour());
            prisonerToUpdate.setAddress(newPrisoner.getAddress());

            prisonersRepository.save(prisonerToUpdate);
            return Optional.of(prisonerToUpdate);
        } else {
            return Optional.empty();
        }
    }

}
