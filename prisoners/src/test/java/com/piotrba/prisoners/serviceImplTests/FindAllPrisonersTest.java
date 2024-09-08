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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindAllPrisonersTest {

    private PrisonerServiceImpl prisonerServiceImpl;
    private PrisonersRepository prisonersRepository;

    List<Prisoner> prisonerList = new ArrayList<>(Arrays.asList(
            new Prisoner(1L, "John", "Doe", LocalDateTime.now(), LocalDateTime.now().plusYears(5), ImprisonmentRigour.MAXIMUM_SECURITY, new Address("123 Main St", "12345", "Springfield")),
            new Prisoner(2L, "Jane", "Doe", LocalDateTime.now(), LocalDateTime.now().plusYears(3), ImprisonmentRigour.MINIMUM_SECURITY, new Address("456 Elm St", "54321", "Shelbyville"))
    ));

    @BeforeEach
    public void setUp() {
        prisonersRepository = Mockito.mock(PrisonersRepository.class);
        prisonerServiceImpl = new PrisonerServiceImpl(prisonersRepository);
    }

    @Test
    public void testFindAllPrisoners_PrisonsExist() {
        when(prisonersRepository.findAll()).thenReturn(prisonerList);
        List<Prisoner> result = prisonerServiceImpl.findAll();
        assertEquals(prisonerList, result);
    }

    @Test
    public void testFindAllPrisoners_NoPrisonersExist() {
        when(prisonersRepository.findAll()).thenReturn(new ArrayList<>());
        List<Prisoner> result = prisonerServiceImpl.findAll();
        assertTrue(result.isEmpty());
    }
}
