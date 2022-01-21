package com.example.accessingdatamysql.invitation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InvitationServiceTests {
    @Autowired
    protected InvitationService invitationService;

    @Test
    @Transactional
    public void shouldInsertInvitation() {
        List<Invitation> invitations = this.invitationService.findAllInvitations();
        int found = invitations.size();

        LocalDateTime creationDate = LocalDateTime.now();
        Invitation invitation = new Invitation(creationDate);
        this.invitationService.saveInvitation(invitation);
        assertNotEquals(invitation.getId(), 0L);

        invitations = this.invitationService.findAllInvitations();
        assertEquals(invitations.size(), found + 1);
    }

    @Test
    @Transactional(readOnly = true)
    public void shouldFindSingleInvitation() {
        Optional<Invitation> invitationOpt = this.invitationService.findInvitation(1L);
        if (invitationOpt.isPresent()) {
            Invitation invitation = invitationOpt.get();
            assertEquals(invitation.getId(), 1L);
        }
    }

    @Test
    @Transactional
    void shouldDeleteInvitation() {
        LocalDateTime creationDate = LocalDateTime.now();
        Invitation invitation = new Invitation(creationDate);
        invitation = this.invitationService.saveInvitation(invitation);
        List<Invitation> invitations = this.invitationService.findAllInvitations();
        int found = invitations.size();

        this.invitationService.deleteInvitation(invitation.getId());
        invitations = this.invitationService.findAllInvitations();
        assertEquals(invitations.size(), found - 1);

    }

}
