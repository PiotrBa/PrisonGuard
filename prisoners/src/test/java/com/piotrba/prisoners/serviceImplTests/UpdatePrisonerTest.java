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
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class UpdatePrisonerTest {

    private PrisonersRepository prisonersRepository;
    private PrisonerServiceImpl prisonerService;

    Prisoner prisoner = new Prisoner(1L, "John", "Doe", LocalDateTime.now(), LocalDateTime.now().plusYears(5), ImprisonmentRigour.MAXIMUM_SECURITY, new Address("123 Main St", "12345", "Springfield"));
    Prisoner updatedPrisoner = new Prisoner(1L, "John", "Smith", LocalDateTime.now(), LocalDateTime.now().plusYears(5), ImprisonmentRigour.MAXIMUM_SECURITY, new Address("123 Main St", "12345", "Springfield"));

    @BeforeEach
    public void setUp() {
        prisonersRepository = Mockito.mock(PrisonersRepository.class);
        prisonerService = new PrisonerServiceImpl(prisonersRepository);
    }

    @Test
    public void testUpdatePrisoner_PrisonsExist() {
        when(prisonersRepository.findById(1L)).thenReturn(Optional.of(prisoner));
        when(prisonersRepository.save(updatedPrisoner)).thenReturn(updatedPrisoner);
        Optional<Prisoner> result = prisonerService.updatePrisoner(1L, updatedPrisoner);
        assertEquals(updatedPrisoner, result.get());
    }

    @Test
    public void testUpdatePrisoner_PrisonerDoesNotExist() {
        when(prisonersRepository.findById(1L)).thenReturn(Optional.empty());
        try {
            prisonerService.updatePrisoner(1L, updatedPrisoner);
            fail("Expected an IllegalStateException to be thrown");
        } catch (IllegalStateException e) {
            assertEquals("Prisoner does not exists", e.getMessage());
        }
    }
}
