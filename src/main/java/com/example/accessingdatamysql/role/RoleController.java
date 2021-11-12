package com.example.accessingdatamysql.role;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping(path = "/roles") // Map ONLY POST Requests
    public @ResponseBody Role addNewRole(@RequestBody Role role) {
        return this.roleService.saveRole(role);
    }

    @GetMapping(path = "/roles")
    public @ResponseBody Iterable<Role> getAllRoles() {
        return this.roleService.findAllRoles();
    }

    @GetMapping(path = "/roles/{id}")
    public @ResponseBody Optional<Role> getRoleById(@PathVariable Long id) {
        return this.roleService.findRole(id);
    }

    @DeleteMapping(path = "/roles/{id}")
    public @ResponseBody String deleteRole(@PathVariable Long id) {
        this.roleService.deleteRole(id);
        return "Deleted";
    }

    @DeleteMapping(path = "/roles")
    public @ResponseBody String deleteAllRoles() {
        this.roleService.deleteAllRoles();
        return "Deleted all";
    }

    @PutMapping("/roles/{id}")
    public @ResponseBody Role updateRole(@RequestBody Role newRole, @PathVariable Long id) {
        this.roleService.findRole(id).map(role -> {
            role.setName(newRole.getName());
            return this.roleService.saveRole(role);
        }).orElseGet(() -> {
            newRole.setId(id);
            return this.roleService.saveRole(newRole);
        });
        return newRole;
    }
}
