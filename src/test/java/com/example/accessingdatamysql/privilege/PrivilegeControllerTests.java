package com.example.accessingdatamysql.privilege;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.util.Lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@WebMvcTest(controllers = PrivilegeController.class)
public class PrivilegeControllerTests {

    private static final Long TEST_PRIVILEGE_ID = 1L;

    @MockBean
    private PrivilegeService privilegeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Privilege privilege = new Privilege("CREATE_GAMES");
        privilege.setId(TEST_PRIVILEGE_ID);
        given(this.privilegeService.findAllPrivileges()).willReturn(Lists.newArrayList(privilege));

        given(this.privilegeService.findPrivilege(TEST_PRIVILEGE_ID)).willReturn(Optional.of(privilege));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/privileges")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/privileges/{privilegeId}", TEST_PRIVILEGE_ID)).andExpect(status().isOk());
    }

    @Test
    void testProcessCreationSuccess() throws Exception {
        Privilege privilege = new Privilege("VIEW_GAMES");
        mockMvc.perform(post("/api/privileges").contentType("application/json")
                .content(objectMapper.writeValueAsString(privilege))).andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/privileges/{privilegeId}", TEST_PRIVILEGE_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/privileges")).andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Privilege privilege = new Privilege();
        privilege.setName("VIEW_AWARDS");
        mockMvc.perform(put("/api/privileges/{id}", TEST_PRIVILEGE_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(privilege))).andExpect(status().isOk());
    }
}
