package com.vector.authorbookrest.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vector.authorbookcommon.dto.SaveAuthorRequest;
import com.vector.authorbookcommon.entity.Gender;
import com.vector.authorbookcommon.repository.AuthorRepository;
import com.vector.authorbookcommon.service.AuthorService;
import com.vector.authorbookcommon.service.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class AuthorEndpointTest {

    @MockitoBean
    private TestService testService;


    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        authorRepository.deleteAll();
    }


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getAll() throws Exception {
        doNothing().when(testService).test();

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
    void create() throws Exception {
        SaveAuthorRequest saveAuthorRequest = SaveAuthorRequest.builder()
                .dateOfBirthday(new Date())
                .phone("874515919")
                .name("zaven")
                .surname("zavenyan")
                .gender(Gender.MALE)
                .build();

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(saveAuthorRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("zaven"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void createConflict() throws Exception {
        SaveAuthorRequest saveAuthorRequest = SaveAuthorRequest.builder()
                .dateOfBirthday(new Date())
                .phone("874515919")
                .name("zaven")
                .surname("zavenyan")
                .gender(Gender.MALE)
                .build();

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(saveAuthorRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("zaven"))
                .andExpect(jsonPath("$.id").isNotEmpty());

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(saveAuthorRequest)))
                .andExpect(status().isConflict());
    }

    @Test
    void delete() {
    }
}