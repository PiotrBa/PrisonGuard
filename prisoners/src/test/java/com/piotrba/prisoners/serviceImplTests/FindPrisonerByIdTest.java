package com.piotrba.prisoners.serviceImplTests;

import com.piotrba.prisoners.entity.Address;
import com.piotrba.prisoners.entity.ImprisonmentRigour;
import com.piotrba.prisoners.entity.Prisoner;
import com.piotrba.prisoners.repo.PrisonersRepository;
import com.piotrba.prisoners.service.serviceImpl.PrisonerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class FindPrisonerByIdTest {

    private PrisonerServiceImpl prisonerService;
    private PrisonersRepository prisonersRepository;
    Prisoner prisoner = new Prisoner(1L, "John", "Doe", LocalDateTime.now(), LocalDateTime.now().plusYears(5), ImprisonmentRigour.MAXIMUM_SECURITY, new Address("123 Main St", "12345", "Springfield"));

    @BeforeEach
    public void setUp() {
        prisonersRepository = Mockito.mock(PrisonersRepository.class);
        prisonerService = new PrisonerServiceImpl(prisonersRepository);
    }

    @Test
    public void testFindPrisonerById_PrisonsExist() {
        when(prisonersRepository.findById(1L)).thenReturn(Optional.of(prisoner));
        Optional<Prisoner> result = prisonerService.findById(1L);
        assertEquals(Optional.of(prisoner), result);
    }

    @Test
    public void testFindPrisonerById_PrisonerDoesNotExist() {
        when(prisonersRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Prisoner> result = prisonerService.findById(2L);
        assertFalse(result.isPresent());
    }
}
