package com.piotrba.visitors.controller;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.service.VisitorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/visitor")
@AllArgsConstructor
public class VisitorsController {

    private final VisitorService visitorsService;

    @GetMapping
    public ResponseEntity<Visitor> getVisitor(@RequestParam String email){
        Optional<Visitor> optional = visitorsService.findByEMail(email);
        return optional
                .map(visitor -> ResponseEntity.ok(visitor))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
