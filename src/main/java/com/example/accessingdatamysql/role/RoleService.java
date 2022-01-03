package com.example.accessingdatamysql.role;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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

    public Optional<Role> findRole(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> findAllRoles() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public void deleteAllRoles() {
        roleRepository.deleteAll();
    }
}
