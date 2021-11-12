package com.example.accessingdatamysql.user;

import java.util.Optional;

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

    public Iterable<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public void deleteAllAdmins() {
        adminRepository.deleteAll();
    }
}
