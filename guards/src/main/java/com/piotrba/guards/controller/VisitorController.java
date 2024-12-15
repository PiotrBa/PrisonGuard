package com.piotrba.guards.controller;

import com.piotrba.guards.client.PrisonerClient;
import com.piotrba.guards.client.VisitorClient;
import com.piotrba.guards.dto.prisoner.PrisonerDTO;
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
    private final PrisonerClient prisonerClient;

    @GetMapping("/all")
    public List<VisitorDTO> getAllVisitors() {
        logger.info("Received request to get all visitors");
        List<VisitorDTO> visitors = visitorClient.getAllVisitors();
        logger.info("Returning {} visitors", visitors.size());
        return visitors;
    }

    @GetMapping("/{id}")
    public VisitorDTO getVisitorById(@PathVariable Long id) {
        logger.info("Received request to get visitor with id: {}", id);
        VisitorDTO visitor = visitorClient.getVisitorById(id);
        if (visitor == null) {
            logger.warn("Visitor with id: {} not found", id);
        } else {
            logger.info("Returning visitor: {}", visitor);
        }
        return visitor;
    }

    @PostMapping("/add")
    public VisitorDTO addNewVisitor(@RequestBody VisitorDTO visitorDTO) {
        logger.info("Received request to add a new visitor: {}", visitorDTO);

        if (visitorDTO.getPrisonerIdNumber() != null) {
            PrisonerDTO prisoner = prisonerClient.getPrisonerById(visitorDTO.getPrisonerIdNumber());
            if (prisoner == null) {
                logger.error("Prisoner with ID {} does not exist", visitorDTO.getPrisonerIdNumber());
                throw new IllegalArgumentException("Prisoner with ID " + visitorDTO.getPrisonerIdNumber() + " does not exist");
            }
            logger.info("Prisoner with ID {} verified for visitor assignment", visitorDTO.getPrisonerIdNumber());
        }

        VisitorDTO savedVisitor = visitorClient.addVisitor(visitorDTO);
        logger.info("Visitor added with id: {}", savedVisitor.getId());
        return savedVisitor;
    }

    @PostMapping("/update/{id}")
    public VisitorDTO updateVisitor(@PathVariable Long id, @RequestBody VisitorDTO visitorDTO) {
        logger.info("Received request to update visitor with id: {}", id);
        VisitorDTO updatedVisitor = visitorClient.updateVisitor(id, visitorDTO);
        logger.info("Visitor with id: {} updated successfully", id);
        return updatedVisitor;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVisitor(@PathVariable Long id) {
        logger.info("Received request to delete visitor with id: {}", id);
        visitorClient.deleteVisitor(id);
        logger.info("Visitor with id: {} deleted successfully", id);
    }
}
