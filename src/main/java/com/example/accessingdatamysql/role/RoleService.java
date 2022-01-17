package com.example.accessingdatamysql.role;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

  private RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Transactional
  public Role saveRole(Role role) throws DataAccessException {
    roleRepository.save(role);
    return role;
  }

  @Transactional(readOnly = true)
  public Optional<Role> findRole(Long id) {
    return roleRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Role> findAllRoles() {
    return StreamSupport
        .stream(roleRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteRole(Long id) {
    roleRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllRoles() {
    roleRepository.deleteAll();
  }
}
