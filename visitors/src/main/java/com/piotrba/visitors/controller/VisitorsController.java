package com.piotrba.visitors.controller;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.service.VisitorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/visitor")
@AllArgsConstructor
public class VisitorsController {

    private static final Logger logger = LoggerFactory.getLogger(VisitorsController.class);

    private final VisitorService visitorsService;

    @GetMapping
    public ResponseEntity<Visitor> getVisitor(@RequestParam String email) {
        logger.info("Received request to get visitor by email: {}", email);

        Optional<Visitor> optional = visitorsService.findByEMail(email);
        if (optional.isPresent()) {
            logger.info("Visitor with email: {} found", email);
            return ResponseEntity.ok(optional.get());
        } else {
            logger.warn("Visitor with email: {} not found", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
