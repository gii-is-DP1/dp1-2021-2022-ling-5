package com.example.accessingdatamysql.role;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.privilege.Privilege;
import com.example.accessingdatamysql.privilege.PrivilegeService;
import com.example.accessingdatamysql.user.Player;

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

    @PostMapping(value = "/roles")
    public @ResponseBody Role addNewRole(@RequestBody Role role) {
        role.setPlayers(new ArrayList<Player>());
        role.setPrivileges(new ArrayList<Privilege>());

        return this.roleService.saveRole(role);
    }

    @PostMapping(value = "/roles/{roleId}/privileges/{privilegeId}")
    public @ResponseBody Role addNewPrivilegeToRole(@PathVariable Long roleId, @PathVariable Long privilegeId) {
        Optional<Role> role = this.roleService.findRole(roleId);
        Optional<Privilege> privilege = this.privilegeService.findPrivilege(privilegeId);
        if (privilege.isPresent()) {

            if (role.get().getPrivileges() == null)
                role.get().setPrivileges(new ArrayList<Privilege>());
            if (privilege.get().getRoles() == null)
                privilege.get().setRoles(new ArrayList<Role>());

            if (!role.get().getPrivileges().contains(privilege.get()))
                role.get().getPrivileges().add(privilege.get());
            if (!privilege.get().getRoles().contains(role.get()))
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
        if (!privilege.isPresent())
            return "Privilege not found";

        if (role.get().getPrivileges() == null) {
            role.get().setPrivileges(new ArrayList<Privilege>());
            if (privilege.get().getRoles() == null)
                privilege.get().setRoles(new ArrayList<Role>());
            return "This role doesn't have privileges";
        }

        role.get().getPrivileges().remove(privilege.get());
        privilege.get().getRoles().remove(role.get());
        return "Privilege deleted from role";

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
