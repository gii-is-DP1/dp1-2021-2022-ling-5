package com.example.accessingdatamysql.role;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.user.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(value = "/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/roles")
    public @ResponseBody Role addNewRole(@RequestBody Role role) {
        try {
            role.setPlayers(new ArrayList<Player>());
            return this.roleService.saveRole(role);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping(value = "/roles")
    public @ResponseBody Iterable<Role> getAllRoles() {
        return this.roleService.findAllRoles();
    }

    @GetMapping(value = "/roles/{id}")
    public @ResponseBody Role getRoleById(@PathVariable Long id) {
        Optional<Role> rOptional = this.roleService.findRole(id);
        if (rOptional.isPresent())
            return rOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
    }

    @DeleteMapping(value = "/roles/{id}")
    public @ResponseBody void deleteRole(@PathVariable Long id) {
        Optional<Role> rOptional = this.roleService.findRole(id);
        if (rOptional.isPresent()) {
            this.roleService.deleteRole(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Role deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
    }

    @DeleteMapping(value = "/roles")
    public @ResponseBody void deleteAllRoles() {
        this.roleService.deleteAllRoles();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Roles deleted");
    }

    @PutMapping(value = "/roles/{id}")
    public @ResponseBody Role updateRole(@RequestBody Role newRole, @PathVariable Long id) {
        Optional<Role> rOptional = this.roleService.findRole(id);
        if (!rOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        try {
            Role role = rOptional.get();
            role.setName(newRole.getName());
            return this.roleService.saveRole(role);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
