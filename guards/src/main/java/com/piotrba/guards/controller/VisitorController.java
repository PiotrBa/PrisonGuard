package com.piotrba.guards.controller;

import com.piotrba.guards.client.PrisonerClient;
import com.piotrba.guards.client.VisitorClient;
import com.piotrba.guards.dto.prisoner.PrisonerDTO;
import com.piotrba.guards.dto.visitor.VisitorDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guard/visitor")
@AllArgsConstructor
public class VisitorController {

    private final VisitorClient visitorClient;
    private final PrisonerClient prisonerClient;

    @GetMapping("/all")
    public List<VisitorDTO> getAllVisitors(){
        return visitorClient.getAllVisitors();
    }

    @GetMapping("/{id}")
    public VisitorDTO getVisitorById(@PathVariable Long id){
        return visitorClient.getVisitorById(id);
    }

    @PostMapping("/add")
    public VisitorDTO addNewVisitor(@RequestBody VisitorDTO visitorDTO) {
        if (visitorDTO.getPrisonerIdNumber() != null) {
            PrisonerDTO prisoner = prisonerClient.getPrisonerById(visitorDTO.getPrisonerIdNumber());
            if (prisoner == null) {
                throw new IllegalArgumentException("Prisoner with ID " + visitorDTO.getPrisonerIdNumber() + " does not exist");
            }
        }

        return visitorClient.addVisitor(visitorDTO);
    }
    @PostMapping("/ipdate/{id}")
    public VisitorDTO updateVisitor(@PathVariable Long id, @RequestBody VisitorDTO visitorDTO){
        return visitorClient.updateVisitor(id, visitorDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVisitor(@PathVariable Long id){
        visitorClient.deleteVisitor(id);
    }
}
