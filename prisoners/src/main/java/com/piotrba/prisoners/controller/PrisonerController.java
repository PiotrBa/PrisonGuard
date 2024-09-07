package com.piotrba.prisoners.controller;

import com.piotrba.prisoners.entity.Prisoner;
import com.piotrba.prisoners.service.PrisonerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prisoner")
@AllArgsConstructor
public class PrisonerController {

    private final PrisonerService prisonerService;


    @GetMapping()
    public ResponseEntity<List<Prisoner>> getAllPrisoners(){
        List<Prisoner> prisoners = prisonerService.findAll();
        return Optional.ofNullable(prisoners)
                .filter(list-> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Prisoner> getPrisonerById (@PathVariable Long id){
        Optional<Prisoner> prisonerOptional = prisonerService.findById(id);
        return prisonerOptional
                .map(prisoner -> ResponseEntity.ok(prisoner))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Prisoner> updatePrisoner (@PathVariable Long id, @RequestBody Prisoner prisoner){
        Optional<Prisoner> prisonerOptional = prisonerService.updatePrisoner(id, prisoner);
        return prisonerOptional
                .map(prisoner1 -> ResponseEntity.ok(prisoner))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
