package com.example.accessingdatamysql.privilege;

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
@RequestMapping(path = "/api")
public class PrivilegeController {
    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping(path = "/privileges", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody Privilege addNewPrivilege(@RequestBody Privilege privilege) {
        return this.privilegeService.savePrivilege(privilege);
    }

    @RequestMapping(path = "/privileges", method = RequestMethod.GET)
    public @ResponseBody Iterable<Privilege> getAllPrivileges() {
        return this.privilegeService.findAllPrivileges();
    }

    @RequestMapping(path = "/privileges/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Privilege> getPrivilegeById(@PathVariable Long id) {
        return this.privilegeService.findPrivilege(id);
    }

    @RequestMapping(path = "/privileges/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deletePrivilege(@PathVariable Long id) {
        this.privilegeService.deletePrivilege(id);
        return "Deleted";
    }

    @RequestMapping(path = "/privileges", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAllPrivileges() {
        this.privilegeService.deleteAllPrivileges();
        return "Deleted all";
    }

    @RequestMapping(path = "/privileges/{id}", method = RequestMethod.PUT)
    public @ResponseBody Privilege updatePrivilege(@RequestBody Privilege newPrivilege, @PathVariable Long id) {
        this.privilegeService.findPrivilege(id).map(privilege -> {
            privilege.setName(newPrivilege.getName());
            return this.privilegeService.savePrivilege(privilege);
        }).orElseGet(() -> {
            newPrivilege.setId(id);
            return this.privilegeService.savePrivilege(newPrivilege);
        });
        return newPrivilege;
    }
}
