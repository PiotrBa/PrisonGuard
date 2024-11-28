package com.piotrba.visitors.service.serviceImpl;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.repo.VisitorsRepository;
import com.piotrba.visitors.service.VisitorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final VisitorsRepository visitorsRepository;

    @Override
    public List<Visitor> findAllVisitors() {
        return visitorsRepository.findAll();
    }

    @Override
    public Optional<Visitor> findVisitorById(Long id) {
        return visitorsRepository.findById(id);
    }

    @Override
    public Visitor addNewVisitor(Visitor visitor) {
        return visitorsRepository.save(visitor);
    }

    @Override
    public Visitor updateVisitor(Long id, Visitor newVisitor) {
        Optional<Visitor> optionalVisitor = visitorsRepository.findById(id);
        if (optionalVisitor.isEmpty()){
            throw new IllegalArgumentException("Visitor is not exist");
        }
        Visitor existingVisitor = optionalVisitor.get();
        existingVisitor.setId(newVisitor.getId());
        existingVisitor.setFirstName(newVisitor.getFirstName());
        existingVisitor.setLastName(newVisitor.getLastName());
        existingVisitor.setPhoneNumber(newVisitor.getPhoneNumber());
        existingVisitor.setAddress(newVisitor.getAddress());
        existingVisitor.setEmail(newVisitor.getEmail());
        existingVisitor.setPrisonerIdNumber(newVisitor.getPrisonerIdNumber());
        existingVisitor.setRelationshipToPrisoner(newVisitor.getRelationshipToPrisoner());
        return existingVisitor;
    }

    @Override
    public void deleteVisitor(Long id) {
        visitorsRepository.deleteById(id);
    }
}
