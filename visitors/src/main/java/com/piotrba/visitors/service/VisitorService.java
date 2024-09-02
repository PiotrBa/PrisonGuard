package com.piotrba.visitors.service;

import com.piotrba.visitors.entity.Visitor;

import java.util.Optional;

public interface VisitorService {

    Optional<Visitor> findByEMail(String email);
}
