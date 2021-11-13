package com.example.accessingdatamysql.role;

import java.util.Optional;

import com.example.accessingdatamysql.privilege.Privilege;
import com.example.accessingdatamysql.privilege.PrivilegeService;

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
@RequestMapping(value = "/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping(value = "roles/{roleId}/privileges/{privilegeId}")
    public @ResponseBody Role addNewPrivilegeToUser(@PathVariable Long roleId, @PathVariable Long privilegeId) {
        Optional<Role> role = this.roleService.findRole(roleId);
        Optional<Privilege> privilege = this.privilegeService.findPrivilege(privilegeId);
        if (!role.isPresent())
            return null;
        if (privilege.isPresent()) {
            role.get().getPrivileges().add(privilege.get());
            privilege.get().getRoles().add(role.get());
            return this.roleService.saveRole(role.get());
        }
        return null;
    }

    @GetMapping(value = "/roles")
    public @ResponseBody Iterable<Role> getAllRoles() {
        return this.roleService.findAllRoles();
    }

    @GetMapping(value = "/roles/{id}")
    public @ResponseBody Optional<Role> getRoleById(@PathVariable Long id) {
        return this.roleService.findRole(id);
    }

    @DeleteMapping(value = "/roles/{id}")
    public @ResponseBody String deleteRole(@PathVariable Long id) {
        this.roleService.deleteRole(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/roles")
    public @ResponseBody String deleteAllRoles() {
        this.roleService.deleteAllRoles();
        return "Deleted all";
    }

    @DeleteMapping(value = "/roles/{roleId}/privileges/{privilegeId}")
    public @ResponseBody String deletePrivilegeFromRole(@PathVariable Long roleId, @PathVariable Long privilegeId) {
        Optional<Role> role = this.roleService.findRole(roleId);
        Optional<Privilege> privilege = this.privilegeService.findPrivilege(privilegeId);
        if (!role.isPresent())
            return "User not found";
        else if (!privilege.isPresent())
            return "Privilege not found";
        else {
            role.get().getPrivileges().remove(privilege.get());
            privilege.get().getRoles().remove(role.get());
            return "Privilege deleted from role";
        }
    }

    @PutMapping(value = "/roles/{id}")
    public @ResponseBody Role updateRole(@RequestBody Role newRole, @PathVariable Long id) {
        this.roleService.findRole(id).map(role -> {
            role.setName(newRole.getName());
            return this.roleService.saveRole(role);
        }).orElse(null);
        return null;
    }
}
