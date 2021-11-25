package com.example.accessingdatamysql.privilege;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.role.Role;

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
public class PrivilegeController {
    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping(value = "/privileges") // Map ONLY POST Requests
    public @ResponseBody Privilege addNewPrivilege(@RequestBody Privilege privilege) {
        privilege.setRoles(new ArrayList<Role>());

        return this.privilegeService.savePrivilege(privilege);
    }

    @GetMapping(value = "/privileges")
    public @ResponseBody Iterable<Privilege> getAllPrivileges() {
        return this.privilegeService.findAllPrivileges();
    }

    @GetMapping(value = "/privileges/{id}")
    public @ResponseBody Optional<Privilege> getPrivilegeById(@PathVariable Long id) {
        return this.privilegeService.findPrivilege(id);
    }

    @DeleteMapping(value = "/privileges/{id}")
    public @ResponseBody String deletePrivilege(@PathVariable Long id) {
        this.privilegeService.deletePrivilege(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/privileges")
    public @ResponseBody String deleteAllPrivileges() {
        this.privilegeService.deleteAllPrivileges();
        return "Deleted all";
    }

    @PutMapping(value = "/privileges/{id}")
    public @ResponseBody Privilege updatePrivilege(@RequestBody Privilege newPrivilege, @PathVariable Long id) {
        this.privilegeService.findPrivilege(id).map(privilege -> {
            privilege.setName(newPrivilege.getName());
            return this.privilegeService.savePrivilege(privilege);
        }).orElse(null);
        return null;
    }
}
