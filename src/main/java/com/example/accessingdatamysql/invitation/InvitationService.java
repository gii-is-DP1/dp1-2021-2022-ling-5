package com.example.accessingdatamysql.invitation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvitationService {

    private InvitationRepository invitationRepository;

    @Autowired
    public InvitationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Transactional
    public Invitation saveInvitation(Invitation n) throws DataAccessException {
        invitationRepository.save(n);
        return n;
    }

    @Transactional(readOnly = true)
    public List<Invitation> findAllInvitations() throws DataAccessException {
        return StreamSupport
                .stream(invitationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Invitation> findInvitation(Long id) throws DataAccessException {
        return invitationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Invitation> findAllInvitationsByRequester(Long requesterId) throws DataAccessException {
        return StreamSupport
                .stream(invitationRepository.findAll().spliterator(), false)
                .filter(invitation -> invitation.getRequester().getId() == requesterId)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Invitation> findAllInvitationsByRequested(Long requestedId) throws DataAccessException {
        return StreamSupport
                .stream(invitationRepository.findAll().spliterator(), false)
                .filter(invitation -> invitation.getRequested().getId() == requestedId)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteInvitation(Long id) throws DataAccessException {
        invitationRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllInvitations() throws DataAccessException {
        invitationRepository.deleteAll();
    }

    @Transactional
    public void deleteAllInvitationsByRequester(Long playerId) throws DataAccessException {
        findAllInvitationsByRequester(playerId)
                .stream()
                .forEach(
                        invitation -> invitationRepository.deleteById(invitation.getId()));
    }

    @Transactional
    public void deleteAllInvitationsByRequested(Long playerId) throws DataAccessException {
        findAllInvitationsByRequested(playerId)
                .stream()
                .forEach(
                        invitation -> invitationRepository.deleteById(invitation.getId()));
    }
}
