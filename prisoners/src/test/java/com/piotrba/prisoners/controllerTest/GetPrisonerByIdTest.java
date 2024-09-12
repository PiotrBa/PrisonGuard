package com.piotrba.prisoners.controllerTest;

import com.piotrba.prisoners.entity.Address;
import com.piotrba.prisoners.entity.Prisoner;
import com.piotrba.prisoners.entity.ImprisonmentRigour;
import com.piotrba.prisoners.repo.PrisonersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPrisonerByIdTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PrisonersRepository prisonersRepository;

    private Prisoner prisoner;

    @BeforeEach
    public void setUp() {
        prisonersRepository.deleteAll();

        Address address = new Address("123 Main St", "12345", "Springfield");
        prisoner = new Prisoner(null, "John", "Doe", LocalDateTime.now(), LocalDateTime.now().plusYears(2), ImprisonmentRigour.MEDIUM_SECURITY, address);
        prisonersRepository.save(prisoner);
    }

    @Test
    public void testGetPrisonerById_PrisonerExists() throws Exception {
        mockMvc.perform(get("/prisoner/{id}", prisoner.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.imprisonmentRigour", is("MEDIUM_SECURITY")))
                .andExpect(jsonPath("$.address.firstLine", is("123 Main St")));
    }

    @Test
    public void testGetPrisonerById_PrisonerDoesNotExist() throws Exception {
        mockMvc.perform(get("/prisoner/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
