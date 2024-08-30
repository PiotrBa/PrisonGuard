package com.piotrba.visitors.service;

import com.piotrba.visitors.entity.Visitor;

import java.util.List;
import java.util.Optional;

public interface VisitorsService {

    Optional<Visitor> findByEMail(String email);

}
