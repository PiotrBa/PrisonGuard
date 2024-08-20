package com.piotrba.guards.controller;

import com.piotrba.guards.entity.Guard;
import com.piotrba.guards.service.GuardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guard")
@AllArgsConstructor
public class GuardController {

    private final GuardService guardsService;

    @GetMapping
    public ResponseEntity<List<Guard>> getAllGuards() {
        List<Guard> guards = guardsService.findAllGuards();
        return Optional.ofNullable(guards)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guard> getGuardById(@PathVariable Long id){
        Optional<Guard> optionalGuard = guardsService.findGuardById(id);
        return optionalGuard
                .map(guard -> ResponseEntity.ok(guard))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/register")
    public ResponseEntity<Guard> getNewGuard(@RequestBody Guard guard){
        try {
            Guard registerGuard = guardsService.registerNewGuard(guard);
            return ResponseEntity.status(HttpStatus.CREATED).body(registerGuard);
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
