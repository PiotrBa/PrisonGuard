package com.piotrba.visitors.entity;

import com.piotrba.visitors.entity.visitorEnum.RelationshipToPrisioner;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Embedded
    private Address address;
    @Email
    private String email;
    private Boolean active;
    private Long prisonerIdNumber;
    @Enumerated(EnumType.STRING)
    private RelationshipToPrisioner relationshipToPrisoner;
}
