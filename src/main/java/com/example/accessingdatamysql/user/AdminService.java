package com.example.accessingdatamysql.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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

    public Optional<Admin> findAdmin(Long id) {
        return adminRepository.findById(id);
    }

    public List<Admin> findAllAdmins() {
        return StreamSupport.stream(adminRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Admin> findAllAdminsByRole(Long roleId) {
        return StreamSupport.stream(adminRepository.findAll().spliterator(), false)
                .filter(admin -> admin.getRole().getId() == roleId).collect(Collectors.toList());
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public void deleteAllAdmins() {
        adminRepository.deleteAll();
    }

    public void deleteAllAdminsByRole(Long roleId) {
        findAllAdminsByRole(roleId).stream().forEach(admin -> adminRepository.deleteById(admin.getId()));
    }
}
