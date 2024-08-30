package com.piotrba.visitors.controller;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.service.VisitorsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/visitor")
@AllArgsConstructor
public class VisitorsController {

    private final VisitorsService visitorsService;

    @GetMapping
    public ResponseEntity<Visitor> getVisitor(@RequestBody String email){
        Optional<Visitor> optional = visitorsService.findByEMail(email);
        return optional
                .map(visitor -> ResponseEntity.ok(visitor))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
