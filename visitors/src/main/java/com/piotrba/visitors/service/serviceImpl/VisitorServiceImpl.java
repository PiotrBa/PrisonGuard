package com.piotrba.visitors.service.serviceImpl;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.repo.VisitorsRepository;
import com.piotrba.visitors.service.VisitorsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitorServiceImpl implements VisitorsService {

    private final VisitorsRepository visitorsRepository;

    @Override
    public Optional<Visitor> findByEMail(String email) {
        Optional<Visitor> findByEmail = visitorsRepository.findByEmail(email);
        if (findByEmail.isEmpty()){
            throw new RuntimeException("Email does not exist.");
        }else {
            return findByEmail;
        }
    }
}
