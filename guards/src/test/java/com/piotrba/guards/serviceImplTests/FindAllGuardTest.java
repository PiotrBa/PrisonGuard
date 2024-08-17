package com.piotrba.guards.serviceImplTests;

import com.piotrba.guards.entity.Guard;
import com.piotrba.guards.repo.GuardsRepository;
import com.piotrba.guards.service.impl.GuardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindAllGuardTest {

    private GuardServiceImpl guardServiceImpl;
    private GuardsRepository guardsRepository;

    List<Guard> guardsList = new ArrayList<>(Arrays.asList(
            new Guard(1L, "John", "Doe", "123456789", "john.doe@example.com", true, true),
            new Guard(2L, "Steve", "Smith", "123456789", "steve.smith@example.com", true, true),
            new Guard(3L, "Emma", "Williams", "123456789", "emma.williams@example.com", true, true)
    ));
    @BeforeEach
    public void setUp(){
        guardsRepository = Mockito.mock(GuardsRepository.class);
        guardServiceImpl = new GuardServiceImpl(guardsRepository);
    }

    @Test
    public void testFindAllGuardTest_GuardsExist(){
        when(guardsRepository.findAll()).thenReturn(guardsList);
        List<Guard> result = guardServiceImpl.findAllGuards();
        assertEquals(guardsList, result);
    }

    @Test
    public void testFindAllGuardTest_GuardsDoesNotExist(){
        when(guardsRepository.findAll()).thenReturn(new ArrayList<>());
        List<Guard> result = guardServiceImpl.findAllGuards();
        assertTrue(result.isEmpty());
    }
}
