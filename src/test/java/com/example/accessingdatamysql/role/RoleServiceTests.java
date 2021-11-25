package com.example.accessingdatamysql.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleServiceTests {
    @Autowired
    protected RoleService roleService;

    @Test
    @Transactional
    public void shouldInsertRole() {
        List<Role> roles = this.roleService.findAllRoles();
        int found = roles.size();

        Role role = new Role("player");
        this.roleService.saveRole(role);
        assertNotEquals(role.getId(), 0L);

        roles = this.roleService.findAllRoles();
        assertEquals(roles.size(), found + 1);
    }

    @Test
    public void shouldFindSingleRole() {
        Optional<Role> roleOpt = this.roleService.findRole(1L);
        if (roleOpt.isPresent()) {
            Role role = roleOpt.get();
            assertEquals(role.getName(), "player");
        }
    }

    @Test
    @Transactional
    void shouldUpdateRole() {
        Optional<Role> role = this.roleService.findRole(1L);
        if (role.isPresent()) {
            String oldName = role.get().getName();
            String newName = oldName + "X";

            role.get().setName(newName);
            this.roleService.saveRole(role.get());

            role = this.roleService.findRole(1L);
            if (role.isPresent()) {
                assertEquals(role.get().getName(), newName);
            }
        }
    }

    @Test
    void shouldDeleteRole() {
        Role role = new Role();
        role.setName("admin");
        role = this.roleService.saveRole(role);
        List<Role> roles = this.roleService.findAllRoles();
        int found = roles.size();

        this.roleService.deleteRole(role.getId());
        roles = this.roleService.findAllRoles();
        assertEquals(roles.size(), found - 1);

    }

}
