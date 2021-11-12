package com.example.accessingdatamysql.change;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ChangeService {
    private ChangeRepository changeRepository;

    @Autowired
    public ChangeService(ChangeRepository changeRepository) {
        this.changeRepository = changeRepository;
    }

    @Transactional
    public Change saveChange(Change change) throws DataAccessException {
        changeRepository.save(change);
        return change;
    }

    public Optional<Change> findChange(Long id) {
        return changeRepository.findById(id);
    }

    public Iterable<Change> findAllChanges() {
        return changeRepository.findAll();
    }

    public void deleteChange(Long id) {
        changeRepository.deleteById(id);
    }

    public void deleteAllChanges() {
        changeRepository.deleteAll();
    }
}
