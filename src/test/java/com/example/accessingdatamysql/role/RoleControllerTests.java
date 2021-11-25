package com.example.accessingdatamysql.role;

import com.example.accessingdatamysql.privilege.Privilege;
import com.example.accessingdatamysql.privilege.PrivilegeService;
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

@WebMvcTest(controllers = RoleController.class)
public class RoleControllerTests {

    private static final Long TEST_ROLE_ID = 1L;

    private static final Long TEST_PRIVILEGE_ID = 1L;

    @MockBean
    private RoleService roleService;

    @MockBean
    private PrivilegeService privilegeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Role role = new Role("admin");
        role.setId(TEST_ROLE_ID);
        given(this.roleService.findAllRoles()).willReturn(Lists.newArrayList(role));

        Privilege privilege = new Privilege("CREATE_GAMES");
        privilege.setId(TEST_PRIVILEGE_ID);
        given(this.privilegeService.findAllPrivileges()).willReturn(Lists.newArrayList(privilege));

        given(this.roleService.findRole(TEST_ROLE_ID)).willReturn(Optional.of(role));
        given(this.privilegeService.findPrivilege(TEST_PRIVILEGE_ID)).willReturn(Optional.of(privilege));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/roles")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/roles/{roleId}", TEST_ROLE_ID)).andExpect(status().isOk());
    }

    @Test
    void testCreationRole() throws Exception {
        Role role = new Role("admin");
        mockMvc.perform(
                post("/api/roles").contentType("application/json").content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddPrivilegeToRole() throws Exception {
        mockMvc.perform(post("/api/roles/{roleId}/privileges/{privilegeId}", TEST_ROLE_ID, TEST_PRIVILEGE_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/roles/{roleId}", TEST_ROLE_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/roles")).andExpect(status().isOk());
    }

    @Test
    void testDeletePrivilegeFromRole() throws Exception {
        mockMvc.perform(delete("/api/roles/{roleId}/privileges/{privilegeId}", TEST_ROLE_ID, TEST_PRIVILEGE_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Role role = new Role();
        role.setName("admin");
        mockMvc.perform(put("/api/roles/{id}", TEST_ROLE_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(role))).andExpect(status().isOk());
    }
}
