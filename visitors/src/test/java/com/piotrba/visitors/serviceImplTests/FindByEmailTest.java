package com.piotrba.visitors.serviceImplTests;

import com.piotrba.visitors.entity.Address;
import com.piotrba.visitors.entity.Visitor;
import com.piotrba.visitors.entity.visitorEnum.RelationshipToPrisioner;
import com.piotrba.visitors.repo.VisitorsRepository;
import com.piotrba.visitors.service.serviceImpl.VisitorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class FindByEmailTest {

    private VisitorsRepository visitorsRepository;
    private VisitorServiceImpl visitorService;

    Visitor visitor = new Visitor(1L, "John", "Doe", "123456789",
            new Address("123 Main St", "12345", "New York"),
            "john.doe@example.com", true, 101L, RelationshipToPrisioner.FRIEND);

    @BeforeEach
    public void setUp() {
        visitorsRepository = Mockito.mock(VisitorsRepository.class);
        visitorService = new VisitorServiceImpl(visitorsRepository);
    }

    @Test
    public void testFindByEmail_EmailExists() {
        when(visitorsRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(visitor));
        Optional<Visitor> result = visitorService.findByEMail("john.doe@example.com");
        assertTrue(result.isPresent());
        assertEquals(visitor, result.get());
    }

    @Test
    public void testFindByEmail_EmailDoesNotExist() {
        when(visitorsRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());
        try {
            visitorService.findByEMail("nonexistent@example.com");
            fail("Expected a RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Email does not exist.", e.getMessage());
        }
    }
}
