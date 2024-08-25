package com.piotrba.guards.serviceImplTests;

import com.piotrba.guards.entity.Address;
import com.piotrba.guards.entity.Guard;
import com.piotrba.guards.repo.GuardsRepository;
import com.piotrba.guards.service.impl.GuardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class FindGuardByIdTest {

    private GuardServiceImpl guardService;
    private GuardsRepository guardsRepository;
    Guard guard = new Guard(1L, "John", "Doe", "123456789", new Address("123 Main St", "12345", "Springfield"), "john.doe@example.com", true, true);

    @BeforeEach
    public void setUp(){
        guardsRepository = Mockito.mock(GuardsRepository.class);
        guardService = new GuardServiceImpl(guardsRepository);
    }

    @Test
    public void testFindGuardById_GuardExist(){
        when(guardsRepository.findById(1L)).thenReturn(Optional.of(guard));
        Optional<Guard> result = guardService.findGuardById(1L);
        assertEquals(Optional.of(guard), result);
    }

    @Test
    public void testFindGuardById_GuardDoesNotExist(){
        when(guardsRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Guard> result = guardService.findGuardById(2L);
        assertFalse(result.isPresent());
    }
}
