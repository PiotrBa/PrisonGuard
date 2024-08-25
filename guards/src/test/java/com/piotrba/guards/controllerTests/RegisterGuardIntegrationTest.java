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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterGuardIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuardsRepository guardsRepository;

    @BeforeEach
    public void setUp() {
        guardsRepository.deleteAll();
    }

    @Test
    public void testRegisterNewGuard_Success() throws Exception {
        String newGuardJson = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "phoneNumber": "123456789",
                    "address": {
                        "firstLine": "123 Main St",
                        "postCode": "12345",
                        "city": "Springfield"
                    },
                    "email": "john.doe@example.com",
                    "grantHighLevelAccess": false,
                    "active": true
                }
                """;

        mockMvc.perform(post("/guard/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newGuardJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")))
                .andExpect(jsonPath("$.grantHighLevelAccess", is(false)))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.address.firstLine", is("123 Main St")))
                .andExpect(jsonPath("$.address.postCode", is("12345")))
                .andExpect(jsonPath("$.address.city", is("Springfield")));
    }

    @Test
    public void testRegisterNewGuard_Conflict() throws Exception {
        Guard existingGuard = new Guard(null, "John", "Doe", "123456789", new Address("123 Main St", "12345", "Springfield"), "john.doe@example.com", false, true);
        guardsRepository.save(existingGuard);

        String newGuardJson = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "phoneNumber": "123456789",
                    "address": {
                        "firstLine": "123 Main St",
                        "postCode": "12345",
                        "city": "Springfield"
                    },
                    "email": "john.doe@example.com",
                    "grantHighLevelAccess": false,
                    "active": true
                }
                """;

        mockMvc.perform(post("/guard/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newGuardJson))
                .andExpect(status().isConflict());
    }
}
