package com.piotrba.guards.controller;

import com.piotrba.guards.entity.Guard;
import com.piotrba.guards.exeptionHandler.GuardNotFoundException;
import com.piotrba.guards.service.GuardService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guard")
@AllArgsConstructor
public class GuardController {

    private static final Logger logger = LoggerFactory.getLogger(GuardController.class);

    private final GuardService guardsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Guard> getAllGuards() {
        logger.info("Received request to get all guards");
        return guardsService.findAllGuards();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Guard getGuardById(@PathVariable Long id) {
        logger.info("Received request to get guard by id: {}", id);
        return guardsService.findGuardById(id)
                .orElseThrow(() -> new GuardNotFoundException("Guard with id " + id + " not found"));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Guard getNewGuard(@RequestBody Guard guard) {
        logger.info("Received request to register a new guard");
        return guardsService.registerNewGuard(guard);
    }

    @PostMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Guard updateGuard(@PathVariable Long id, @RequestBody Guard guard) {
        logger.info("Received request to update guard with id: {}", id);
        return guardsService.updateGuard(id, guard);
    }
}
