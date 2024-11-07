package com.piotrba.guards.dto;

import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrisonerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime incarcerationDate;
    private LocalDateTime imprisonmentEndTime;
    @Enumerated(EnumType.STRING)
    private ImprisonmentRigourDTO imprisonmentRigour;
    @Embedded
    private AddressDTO address;
}
