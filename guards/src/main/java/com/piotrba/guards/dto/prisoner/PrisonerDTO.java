package com.piotrba.guards.dto.prisoner;

import com.piotrba.guards.dto.visitor.VisitorDTO;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PrisonerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime incarcerationDate;
    private LocalDateTime imprisonmentEndDate;
    @Enumerated(EnumType.STRING)
    private ImprisonmentRigourDTO imprisonmentRigour;
    @Embedded
    private AddressDTO address;
    private List<VisitorDTO> visitors;
}
