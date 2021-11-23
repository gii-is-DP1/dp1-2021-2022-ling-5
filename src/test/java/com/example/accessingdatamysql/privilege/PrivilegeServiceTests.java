package com.example.accessingdatamysql.privilege;

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
public class PrivilegeServiceTests {
    @Autowired
    protected PrivilegeService privilegeService;

    @Test
    @Transactional
    public void shouldInsertPrivilege() {
        List<Privilege> privileges = this.privilegeService.findAllPrivileges();
        int found = privileges.size();

        Privilege privilege = new Privilege("VIEWS_AWARDS");
        this.privilegeService.savePrivilege(privilege);
        assertNotEquals(privilege.getId(), 0L);

        privileges = this.privilegeService.findAllPrivileges();
        assertEquals(privileges.size(), found + 1);
    }

    @Test
    public void shouldFindSinglePrivilege() {
        Optional<Privilege> privilegeOpt = this.privilegeService.findPrivilege(1L);
        if (privilegeOpt.isPresent()) {
            Privilege privilege = privilegeOpt.get();
            assertEquals(privilege.getName(), "VIEWS_AWARDS");
        }
    }

    @Test
    @Transactional
    void shouldUpdatePrivilege() {
        Optional<Privilege> privilege = this.privilegeService.findPrivilege(1L);
        if (privilege.isPresent()) {
            String oldName = privilege.get().getName();
            String newName = oldName + "X";

            privilege.get().setName(newName);
            this.privilegeService.savePrivilege(privilege.get());

            privilege = this.privilegeService.findPrivilege(1L);
            if (privilege.isPresent()) {
                assertEquals(privilege.get().getName(), newName);
            }
        }
    }

    @Test
    void shouldDeletePrivilege() {
        Privilege privilege = new Privilege();
        privilege.setName("VIEW_GAMES");
        privilege = this.privilegeService.savePrivilege(privilege);
        List<Privilege> privileges = this.privilegeService.findAllPrivileges();
        int found = privileges.size();

        this.privilegeService.deletePrivilege(privilege.getId());
        privileges = this.privilegeService.findAllPrivileges();
        assertEquals(privileges.size(), found - 1);

    }

}
