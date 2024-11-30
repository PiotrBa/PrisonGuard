package com.piotrba.guards.dto.visitor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class VisitorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Embedded
    private AddressDTO address;
    @Email
    private String email;
    private Long prisonerIdNumber;
    @Enumerated(EnumType.STRING)
    private RelationshipToPrisonerDTO relationshipToPrisoner;
}
