package com.piotrba.visitors.service.serviceImpl;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.repo.VisitorsRepository;
import com.piotrba.visitors.service.VisitorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final VisitorsRepository visitorsRepository;

    @Override
    public Optional<Visitor> findByEMail(String email) {
        return visitorsRepository.findByEmail(email);
    }
}
