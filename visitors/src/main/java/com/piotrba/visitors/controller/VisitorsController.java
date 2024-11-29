package com.piotrba.visitors.controller;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.exeptionHandler.VisitorNotFoundException;
import com.piotrba.visitors.service.VisitorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitor")
@AllArgsConstructor
public class VisitorsController {

    private static final Logger logger = LoggerFactory.getLogger(VisitorsController.class);

    private final VisitorService visitorsService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Visitor> getAllVisitors(){
        logger.info("Received request to get all visitors");
        return visitorsService.findAllVisitors();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Visitor getVisitor(@PathVariable Long id) {
        logger.info("Received request to get visitor by id: {}", id);
        return visitorsService.findVisitorById(id)
                .orElseThrow(()-> new VisitorNotFoundException("Visitor with id: " + id + " not found"));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Visitor getNewVisitor(@RequestBody Visitor visitor){
        logger.info("Received request to register a new visitor");
        return visitorsService.addNewVisitor(visitor);
    }

    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Visitor updateVisitor (@PathVariable Long id, @RequestBody Visitor visitor){
        logger.info("Received request to update visitor with id: {}", id);
        return visitorsService.updateVisitor(id, visitor);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVisitor (@PathVariable Long id){
        logger.info("Received request to delete visitor with id: {}", id);
        visitorsService.deleteVisitor(id);
    }
}
