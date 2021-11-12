package com.example.accessingdatamysql.modification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ModificationService {
    private ModificationRepository modificationRepository;

    @Autowired
    public ModificationService(ModificationRepository modificationRepository) {
        this.modificationRepository = modificationRepository;
    }

    @Transactional
    public Modification saveModification(Modification modification) throws DataAccessException {
        modificationRepository.save(modification);
        return modification;
    }

    public Optional<Modification> findModification(Long id) {
        return modificationRepository.findById(id);
    }

    public Iterable<Modification> findAllModifications() {
        return modificationRepository.findAll();
    }

    public List<Modification> findAllModificationsByAccount(Long accountId) {
        return StreamSupport.stream(modificationRepository.findAll().spliterator(), false)
                .filter(modification -> modification.getAccount().getId() == accountId).collect(Collectors.toList());
    }

    public void deleteModification(Long id) {
        modificationRepository.deleteById(id);
    }

    public void deleteAllModifications() {
        modificationRepository.deleteAll();
    }

    public void deleteAllModificationsByAccount(Long accountId) {
        findAllModificationsByAccount(accountId).stream()
                .forEach(modification -> modificationRepository.deleteById(modification.getId()));
    }
}