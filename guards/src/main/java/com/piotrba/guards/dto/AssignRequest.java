package com.piotrba.guards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignRequest {
    private Long visitorId;
    private Long prisonerId;
    private String relationshipToPrisoner;
}
