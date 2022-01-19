package com.example.accessingdatamysql.user;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.role.Role;
import com.example.accessingdatamysql.role.RoleService;
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

@WebMvcTest(controllers = AdminController.class)
public class AdminControllerTests {

    private static final Long TEST_ADMIN_ID = 1L;

    private static final Long TEST_FIGURE_ID = 1L;

    private static final Long TEST_ROLE_ID = 2L;

    @MockBean
    private AdminService adminService;

    @MockBean
    private FigureService figureService;

    @MockBean
    private RoleService roleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Admin admin = new Admin("name1", "surname1", "password1", "email1", "nickname1");
        admin.setId(TEST_ADMIN_ID);
        given(this.adminService.findAllAdmins()).willReturn(Lists.newArrayList(admin));

        Figure figure = new Figure("abeja");
        figure.setId(TEST_FIGURE_ID);
        given(this.figureService.findAllFigures()).willReturn(Lists.newArrayList(figure));

        Role role = new Role("admin");
        role.setId(TEST_ROLE_ID);
        given(this.roleService.findAllRoles()).willReturn(Lists.newArrayList(role));

        given(this.adminService.findAdmin(TEST_ADMIN_ID)).willReturn(Optional.of(admin));
        given(this.figureService.findFigure(TEST_FIGURE_ID)).willReturn(Optional.of(figure));
        given(this.roleService.findRole(TEST_ROLE_ID)).willReturn(Optional.of(role));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/admins")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/admins/{adminId}", TEST_ADMIN_ID)).andExpect(status().isOk());
    }

    @Test
    void testCreationRole() throws Exception {
        Admin admin = new Admin("name2", "surname2", "password2", "email2", "nickname2");
        mockMvc.perform(post("/api/admins")
                .contentType("application/json").content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/admins/{adminId}", TEST_ADMIN_ID)).andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/admins")).andExpect(status().isNoContent());
    }

    @Test
    void testUpdate() throws Exception {
        Admin admin = new Admin();
        admin.setName("name3");
        mockMvc.perform(put("/api/admins/{id}", TEST_ADMIN_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(admin))).andExpect(status().isOk());
    }

    @Test
    void testUpdateFigureAdmin() throws Exception {
        mockMvc.perform(put("/api/figures/{figureId}/admins/{adminId}", TEST_FIGURE_ID, TEST_ADMIN_ID))
                .andExpect(status().isOk());
    }
}
