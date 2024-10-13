package com.piotrba.visitors.controller;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.exeptionHandler.VisitorNotFoundException;
import com.piotrba.visitors.service.VisitorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitor")
@AllArgsConstructor
public class VisitorsController {

    private static final Logger logger = LoggerFactory.getLogger(VisitorsController.class);

    private final VisitorService visitorsService;

    @GetMapping
    public Visitor getVisitor(@RequestParam String email) {
        logger.info("Received request to get visitor by email: {}", email);
        return visitorsService.findByEMail(email)
                .orElseThrow(()-> new VisitorNotFoundException("Visitor with email " + email + " not found"));
    }
}
