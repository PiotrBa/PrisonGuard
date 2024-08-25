package com.piotrba.guards.controllerTests;

import com.piotrba.guards.entity.Address;
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
public class FindAllGuardsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuardsRepository guardsRepository;

    @BeforeEach
    public void setUp() {
        guardsRepository.deleteAll();
        List<Guard> guards = Arrays.asList(
                new Guard(1L, "John", "Doe", "123456789", new Address("123 Main St", "12345", "Springfield"), "john.doe@example.com", true, true),
                new Guard(2L, "Steve", "Smith", "123456789", new Address("456 Elm St", "54321", "Shelbyville"), "steve.smith@example.com", true, true),
                new Guard(3L, "Emma", "Williams", "123456789", new Address("789 Oak St", "67890", "Capital City"), "emma.williams@example.com", true, true)
        );
        guardsRepository.saveAll(guards);
    }

    @Test
    public void testGetAllGuards() throws Exception {
        mockMvc.perform(get("/guard")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].address.firstLine", is("123 Main St")))
                .andExpect(jsonPath("$[1].firstName", is("Steve")))
                .andExpect(jsonPath("$[1].address.firstLine", is("456 Elm St")))
                .andExpect(jsonPath("$[2].firstName", is("Emma")))
                .andExpect(jsonPath("$[2].address.firstLine", is("789 Oak St")));
    }

    @Test
    public void testGetAllGuards_NoGuardsFound() throws Exception {
        guardsRepository.deleteAll();
        mockMvc.perform(get("/guard")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
