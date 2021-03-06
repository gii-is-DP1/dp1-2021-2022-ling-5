package com.example.accessingdatamysql.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

  private AdminRepository adminRepository;

  @Autowired
  public AdminService(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }

  @Transactional
  public Admin saveAdmin(Admin admin) throws DataAccessException {
    adminRepository.save(admin);
    return admin;
  }

  @Transactional(readOnly = true)
  public Optional<Admin> findAdmin(Long id) throws DataAccessException {
    return adminRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Admin> findAllAdmins() throws DataAccessException {
    return StreamSupport
        .stream(adminRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<Admin> findAllAdminsByRole(Long roleId) throws DataAccessException {
    return StreamSupport
        .stream(adminRepository.findAll().spliterator(), false)
        .filter(admin -> admin.getRole().getId() == roleId)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteAdmin(Long id) throws DataAccessException {
    adminRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllAdmins() throws DataAccessException {
    adminRepository.deleteAll();
  }

  @Transactional
  public void deleteAllAdminsByRole(Long roleId) throws DataAccessException {
    findAllAdminsByRole(roleId)
        .stream()
        .forEach(admin -> adminRepository.deleteById(admin.getId()));
  }

  @Transactional(readOnly = true)
  public List<Admin> findByNickname(String nickname) throws DataAccessException {
    List<Admin> admins = new ArrayList<Admin>();
    for (Admin a : adminRepository.findAll()) {
      if (a.getNickname().equals(nickname)) {
        admins.add(a);
      }
    }
    return admins;
  }
}
