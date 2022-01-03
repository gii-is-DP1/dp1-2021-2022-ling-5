package com.example.accessingdatamysql.privilege;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {

  private PrivilegeRepository privilegeRepository;

  @Autowired
  public PrivilegeService(PrivilegeRepository privilegeRepository) {
    this.privilegeRepository = privilegeRepository;
  }

  @Transactional
  public Privilege savePrivilege(Privilege privilege)
    throws DataAccessException {
    privilegeRepository.save(privilege);
    return privilege;
  }

  public Optional<Privilege> findPrivilege(Long id) {
    return privilegeRepository.findById(id);
  }

  public List<Privilege> findAllPrivileges() {
    return StreamSupport
      .stream(privilegeRepository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  @Transactional
  public void deletePrivilege(Long id) {
    privilegeRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllPrivileges() {
    privilegeRepository.deleteAll();
  }
}
