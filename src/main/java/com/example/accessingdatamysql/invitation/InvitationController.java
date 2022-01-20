package com.example.accessingdatamysql.invitation;

import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(value = "/api")
public class InvitationController {
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private PlayerService playerService;

    @PostMapping(value = "/players/requester/{requesterId}/requested/{requestedId}/invitations")
    public @ResponseBody Invitation addInvitation(@RequestBody Invitation invitation, @PathVariable Long requesterId,
            @PathVariable Long requestedId) {
        Optional<Player> requester = this.playerService.findPlayer(requesterId);
        Optional<Player> requested = this.playerService.findPlayer(requestedId);
        if (!requester.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requester not found");
        if (!requested.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested not found");
        try {
            invitation.setRequester(requester.get());
            invitation.setRequested(requested.get());
            return this.invitationService.saveInvitation(invitation);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/invitations")
    public @ResponseBody Iterable<Invitation> getAllInvitations() {
        return this.invitationService.findAllInvitations();
    }

    @GetMapping(value = "/invitations/{id}")
    public @ResponseBody Invitation getInvitationById(@PathVariable Long id) {
        Optional<Invitation> invitationOpt = this.invitationService.findInvitation(id);
        if (invitationOpt.isPresent())
            return invitationOpt.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invitation not found");
    }

    @GetMapping(value = "/players/requester/{playerId}/invitations")
    public @ResponseBody List<Invitation> getAllInvitationsByRequester(@PathVariable Long playerId) {
        Optional<Player> requester = this.playerService.findPlayer(playerId);
        if (requester.isPresent()) {
            return this.invitationService.findAllInvitationsByRequester(playerId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requester not found");
    }

    @GetMapping(value = "/players/requested/{playerId}/invitations")
    public @ResponseBody List<Invitation> getAllInvitationsByRequested(@PathVariable Long playerId) {
        Optional<Player> requested = this.playerService.findPlayer(playerId);
        if (requested.isPresent()) {
            return this.invitationService.findAllInvitationsByRequested(playerId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested not found");
    }

    @DeleteMapping(value = "/invitations/{id}")
    public @ResponseBody void deleteInvitation(@PathVariable Long id) {
        Optional<Invitation> fOptional = this.invitationService.findInvitation(id);
        if (fOptional.isPresent()) {
            this.invitationService.deleteInvitation(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invitation deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invitation not found");
    }

    @DeleteMapping(value = "/invitations")
    public @ResponseBody void deleteAllInvitations() {
        this.invitationService.deleteAllInvitations();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invitations deleted");
    }

    @DeleteMapping(value = "/players/requester/{playerId}/invitations")
    public @ResponseBody void deleteAllInvitationsByRequester(@PathVariable Long playerId) {
        Optional<Player> pOptional = this.playerService.findPlayer(playerId);
        if (pOptional.isPresent()) {
            this.invitationService.deleteAllInvitationsByRequester(playerId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invitation deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requester not found");

    }

    @DeleteMapping(value = "/players/requested/{playerId}/invitations")
    public @ResponseBody String deleteAllInvitationsByRequested(@PathVariable Long playerId) {
        Optional<Player> pOptional = this.playerService.findPlayer(playerId);
        if (pOptional.isPresent()) {
            this.invitationService.deleteAllInvitationsByRequested(playerId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invitation deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested not found");
    }

    @PutMapping(value = "/players/requested/{requestedId}/invitations/{id}")
    public @ResponseBody Invitation updateInvitation(@RequestBody Invitation newInvitation,
            @PathVariable Long requestedId, @PathVariable Long id) {
        Optional<Invitation> fOptional = this.invitationService.findInvitation(id);
        if (!fOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invitation not found");
        try {
            Invitation invitation = fOptional.get();
            if (invitation.getRequested().getId() == requestedId) {
                return this.invitationService.saveInvitation(invitation);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "The requested given doesn't match with the requested in the invitation");

    }
}
