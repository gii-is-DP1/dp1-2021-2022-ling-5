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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/roles", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody Role addNewRole(@RequestBody Role role) {
        return this.roleService.saveRole(role);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public @ResponseBody Iterable<Role> getAllRoles() {
        return this.roleService.findAllRoles();
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Role> getRoleById(@PathVariable Long id) {
        return this.roleService.findRole(id);
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteRole(@PathVariable Long id) {
        this.roleService.deleteRole(id);
        return "Deleted";
    }

    @RequestMapping(value = "/roles", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAllRoles() {
        this.roleService.deleteAllRoles();
        return "Deleted all";
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT)
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
