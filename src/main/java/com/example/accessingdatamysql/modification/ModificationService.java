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
  public Modification saveModification(Modification modification)
    throws DataAccessException {
    modificationRepository.save(modification);
    return modification;
  }

  public Optional<Modification> findModification(Long id) {
    return modificationRepository.findById(id);
  }

  public List<Modification> findAllModifications() {
    return StreamSupport
      .stream(modificationRepository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  public List<Modification> findAllModificationsByPlayer(Long playerId) {
    return StreamSupport
      .stream(modificationRepository.findAll().spliterator(), false)
      .filter(modification -> modification.getPlayer().getId() == playerId)
      .collect(Collectors.toList());
  }

  public List<Modification> findAllModificationsByAdmin(Long adminId) {
    return StreamSupport
      .stream(modificationRepository.findAll().spliterator(), false)
      .filter(modification -> modification.getAdmin().getId() == adminId)
      .collect(Collectors.toList());
  }

  @Transactional
  public void deleteModification(Long id) {
    modificationRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllModifications() {
    modificationRepository.deleteAll();
  }

  @Transactional
  public void deleteAllModificationsByPlayer(Long playerId) {
    findAllModificationsByPlayer(playerId)
      .stream()
      .forEach(
        modification -> modificationRepository.deleteById(modification.getId())
      );
  }

  @Transactional
  public void deleteAllModificationsByAdmin(Long adminId) {
    findAllModificationsByAdmin(adminId)
      .stream()
      .forEach(
        modification -> modificationRepository.deleteById(modification.getId())
      );
  }
}
