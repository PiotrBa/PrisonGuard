package com.piotrba.visitors.service.serviceImpl;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.repo.VisitorsRepository;
import com.piotrba.visitors.service.VisitorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private static final Logger logger = LoggerFactory.getLogger(VisitorServiceImpl.class);

    private final VisitorsRepository visitorsRepository;

    @Override
    public List<Visitor> findAllVisitors() {
        logger.info("Fetching all visitors");
        return visitorsRepository.findAll();
    }

    @Override
    public Optional<Visitor> findVisitorById(Long id) {
        logger.info("Fetching visitor by ID: {}", id);
        return visitorsRepository.findById(id);
    }

    @Override
    public Visitor addNewVisitor(Visitor visitor) {
        logger.info("Adding new visitor: {}", visitor);
        Visitor savedVisitor = visitorsRepository.save(visitor);
        logger.info("Visitor added successfully: {}", savedVisitor);
        return savedVisitor;
    }

    @Override
    public Visitor updateVisitor(Long id, Visitor newVisitor) {
        logger.info("Updating visitor with ID: {}", id);
        Optional<Visitor> optionalVisitor = visitorsRepository.findById(id);
        if (optionalVisitor.isEmpty()) {
            logger.error("Visitor with ID {} does not exist", id);
            throw new IllegalArgumentException("Visitor does not exist");
        }
        Visitor existingVisitor = optionalVisitor.get();
        existingVisitor.setFirstName(newVisitor.getFirstName());
        existingVisitor.setLastName(newVisitor.getLastName());
        existingVisitor.setPhoneNumber(newVisitor.getPhoneNumber());
        existingVisitor.setAddress(newVisitor.getAddress());
        existingVisitor.setEmail(newVisitor.getEmail());
        existingVisitor.setPrisonerIdNumber(newVisitor.getPrisonerIdNumber());
        existingVisitor.setRelationshipToPrisoner(newVisitor.getRelationshipToPrisoner());
        logger.info("Visitor updated successfully: {}", existingVisitor);
        return visitorsRepository.save(existingVisitor);
    }

    @Override
    public void deleteVisitor(Long id) {
        logger.info("Deleting visitor with ID: {}", id);
        visitorsRepository.deleteById(id);
        logger.info("Visitor with ID {} deleted successfully", id);
    }
}
