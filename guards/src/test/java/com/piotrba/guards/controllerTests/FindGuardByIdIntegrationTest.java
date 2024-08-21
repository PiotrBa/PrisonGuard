package com.piotrba.guards.controllerTests;

import com.piotrba.guards.entity.Guard;
import com.piotrba.guards.repo.GuardsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class FindGuardByIdIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuardsRepository guardsRepository;

    private Guard existingGuard;

    @BeforeEach
    public void setUp() {
        guardsRepository.deleteAll();
        List<Guard> guards = Arrays.asList(
                new Guard(1L, "John", "Doe", "123456789", "john.doe@example.com", true, true),
                new Guard(2L, "Steve", "Smith", "123456789", "steve.smith@example.com", true, true),
                new Guard(3L, "Emma", "Williams", "123456789", "emma.williams@example.com", true, true)
        );
        guardsRepository.saveAll(guards);

        existingGuard = guards.get(0);
    }

    @Test
    public void testFindGuardById_GuardExists() throws Exception {
        mockMvc.perform(get("/guard/{id}", existingGuard.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(existingGuard.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(existingGuard.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(existingGuard.getLastName())))
                .andExpect(jsonPath("$.phoneNumber", is(existingGuard.getPhoneNumber())))
                .andExpect(jsonPath("$.email", is(existingGuard.getEmail())))
                .andExpect(jsonPath("$.grantHighLevelAccess", is(existingGuard.getGrantHighLevelAccess())))
                .andExpect(jsonPath("$.active", is(existingGuard.getActive())));
    }

    @Test
    public void testFindGuardById_GuardDoesNotExist() throws Exception {
        mockMvc.perform(get("/guard/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
