package com.example.accessingdatamysql.invitation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    @Transactional
    public Invitation saveInvitation(Invitation n) {
        invitationRepository.save(n);
        return n;
    }

    @Transactional(readOnly = true)
    public List<Invitation> findAllInvitations() {
        return StreamSupport
                .stream(invitationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Invitation> findInvitation(Long id) {
        return invitationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Invitation> findAllInvitationsByRequester(Long requesterId) {
        return StreamSupport
                .stream(invitationRepository.findAll().spliterator(), false)
                .filter(invitation -> invitation.getRequester().getId() == requesterId)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Invitation> findAllInvitationsByRequested(Long requestedId) {
        return StreamSupport
                .stream(invitationRepository.findAll().spliterator(), false)
                .filter(invitation -> invitation.getRequested().getId() == requestedId)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteInvitation(Long id) {
        invitationRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllInvitations() {
        invitationRepository.deleteAll();
    }

    @Transactional
    public void deleteAllInvitationsByRequester(Long playerId) {
        findAllInvitationsByRequester(playerId)
                .stream()
                .forEach(
                        invitation -> invitationRepository.deleteById(invitation.getId()));
    }

    @Transactional
    public void deleteAllInvitationsByRequested(Long playerId) {
        findAllInvitationsByRequested(playerId)
                .stream()
                .forEach(
                        invitation -> invitationRepository.deleteById(invitation.getId()));
    }
}
