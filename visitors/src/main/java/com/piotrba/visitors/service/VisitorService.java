package com.piotrba.visitors.service;

import com.piotrba.visitors.entity.Visitor;

import java.util.List;
import java.util.Optional;

public interface VisitorService {

    List<Visitor> findAllVisitors();
    Optional<Visitor> findVisitorById(Long id);

    Visitor addNewVisitor(Visitor visitor);

    Visitor updateVisitor(Long id, Visitor visitor);

    void deleteVisitor (Long id);
}
