package com.example.accessingdatamysql.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTests {
    private static final String TEST_NICKNAME_1 = "unito";
    private static final String TEST_NICKNAME_2 = "Jin";
    private static final String TEST_PASSWORD_1 = "uno";
    private static final String TEST_PASSWORD_2 = "dos";

    @MockBean
    private PlayerService playerService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AdminService adminService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLogin() throws Exception{
        RequestLoggin request = new RequestLoggin(TEST_NICKNAME_1, TEST_PASSWORD_1);
        mockMvc.perform(post("/api/login").contentType("application/json").content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
    }
}
