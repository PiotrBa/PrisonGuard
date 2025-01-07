package com.piotrba.guards.controller;

import com.piotrba.guards.client.VisitorClient;
import com.piotrba.guards.dto.visitor.VisitorDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guard/visitor")
@AllArgsConstructor
public class VisitorController {

    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    private final VisitorClient visitorClient;

    @GetMapping("/all")
    public List<VisitorDTO> getAllVisitors() {
        logger.info("Received request to get all visitors");
        return visitorClient.getAllVisitors();
    }

    @GetMapping("/{id}")
    public VisitorDTO getVisitorById(@PathVariable Long id) {
        logger.info("Received request to get visitor by id: {}", id);
        return visitorClient.getVisitorById(id);
    }

    @PostMapping("/add")
    public VisitorDTO addVisitor(@RequestBody VisitorDTO visitorDTO) {
        logger.info("Received request to add a new visitor: {}", visitorDTO);
        return visitorClient.addVisitor(visitorDTO);
    }

    @PostMapping("/update/{id}")
    public VisitorDTO updateVisitor(@PathVariable Long id, @RequestBody VisitorDTO visitorDTO) {
        logger.info("Received request to update visitor with id: {}", id);
        return visitorClient.updateVisitor(id, visitorDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVisitor(@PathVariable Long id) {
        logger.info("Received request to delete visitor with id: {}", id);
        visitorClient.deleteVisitor(id);
    }
}
