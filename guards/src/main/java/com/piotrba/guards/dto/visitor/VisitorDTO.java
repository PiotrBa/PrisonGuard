package com.piotrba.guards.dto.visitor;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class VisitorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private AddressDTO address;
    @Enumerated(EnumType.STRING)
    private RelationshipToPrisonerDTO relationshipToPrisoner;
    private Long prisonerIdNumber;
}

