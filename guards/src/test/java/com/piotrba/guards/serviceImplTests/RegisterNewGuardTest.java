package com.piotrba.guards.serviceImplTests;

import com.piotrba.guards.entity.Guard;
import com.piotrba.guards.repo.GuardsRepository;
import com.piotrba.guards.service.impl.GuardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class RegisterNewGuardTest {

    private GuardsRepository guardsRepository;
    private GuardServiceImpl guardService;

    Guard guard = new Guard(1L, "John", "Doe", "123456789", "john.doe@example.com", true, true);


    @BeforeEach
    public void setUp(){
        guardsRepository = Mockito.mock(GuardsRepository.class);
        guardService = new GuardServiceImpl(guardsRepository);
    }

    @Test
    public void registerNewGuard_withoutExistingGuard() {
        when(guardsRepository.findByEmail(guard.getEmail())).thenReturn(Optional.empty());
        when(guardsRepository.save(guard)).thenReturn(guard);
        Guard registerNewGuard = guardService.registerNewGuard(guard);
        assertEquals(guard, registerNewGuard);
    }

    @Test
    public void registerNewGuard_withExistingGuard(){
        when(guardsRepository.findByEmail(guard.getEmail())).thenReturn(Optional.of(guard));
        try {
            guardService.registerNewGuard(guard);
            fail("Expected an IllegalStateException to be thrown");
        }catch (IllegalStateException e){
            assertEquals("Guard already exists with this email address", e.getMessage());
        }
    }
}
