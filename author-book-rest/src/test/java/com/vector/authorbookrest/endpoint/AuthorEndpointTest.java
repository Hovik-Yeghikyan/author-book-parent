package com.vector.authorbookrest.endpoint;

import com.vector.authorbookcommon.dto.SaveAuthorRequest;
import com.vector.authorbookcommon.entity.Gender;
import com.vector.authorbookcommon.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class AuthorEndpointTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getAll() throws Exception {
        authorService.save(SaveAuthorRequest.builder()
                .dateOfBirthday(new Date())
                .phone("123456")
                .name("martiros")
                .surname("martieosyan")
                .gender(Gender.MALE)
                .build());

        authorService.save(SaveAuthorRequest.builder()
                .dateOfBirthday(new Date())
                .phone("45648645")
                .name("ivan")
                .surname("ivanov")
                .gender(Gender.MALE)
                .build());

        ResultActions resultActions = mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("martiros"))
                .andExpect(jsonPath("$[0].phone").value("123456"))
                .andExpect(jsonPath("$[1].name").value("ivan"))
                .andExpect(jsonPath("$[1].phone").value("45648645"));

    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }
}