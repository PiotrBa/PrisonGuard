package com.piotrba.visitors.serviceImplTests;

import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.repo.VisitorsRepository;
import com.piotrba.visitors.service.serviceImpl.VisitorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindByEmailTest {

    @Mock
    private VisitorsRepository visitorsRepository;
    @InjectMocks
    private VisitorServiceImpl visitorService;

    public Visitor visitor = Visitor.builder()
            .id(1L)
            .firstName("Ryan")
            .lastName("Smith")
            .email("ryan.smith@exampl.com")
            .build();

    @Test
    public void findVisitorByEmail_whenEmailExists_shouldReturnVisitor() {
        when(visitorsRepository.findByEmail("ryan.smith@exampl.com")).thenReturn(Optional.of(visitor));
        Optional<Visitor> result = visitorService.findByEMail("ryan.smith@exampl.com");
        assertEquals(Optional.of(visitor), result);
    }

    @Test
    public void testFindByEmail_whenEmailDoesNotExist_shouldNotReturnAnyVisitor() {
        when(visitorsRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());
        Optional<Visitor> result = visitorService.findByEMail("nonexistent@example.com");
        assertFalse(result.isPresent());
    }
}
