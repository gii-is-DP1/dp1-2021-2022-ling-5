package com.example.accessingdatamysql.user;

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
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admins", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody Admin addNewAdmin(@RequestBody Admin admin) {
        return this.adminService.saveAdmin(admin);
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public @ResponseBody Iterable<Admin> getAllAdmins() {
        return this.adminService.findAllAdmins();
    }

    @RequestMapping(value = "/admins/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Admin> getAdminById(@PathVariable Long id) {
        return this.adminService.findAdmin(id);
    }

    @RequestMapping(value = "/admins/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAdmin(@PathVariable Long id) {
        this.adminService.deleteAdmin(id);
        return "Deleted";
    }

    @RequestMapping(value = "/admins", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAllAdmins() {
        this.adminService.deleteAllAdmins();
        return "Deleted all";
    }

    @RequestMapping(value = "/admins/{id}", method = RequestMethod.PUT)
    public @ResponseBody Admin updateAdmin(@RequestBody Admin newAdmin, @PathVariable Long id) {
        this.adminService.findAdmin(id).map(admin -> {
            admin.setName(newAdmin.getName());
            admin.setSurname(newAdmin.getSurname());
            admin.setPassword(newAdmin.getPassword());
            admin.setEmail(newAdmin.getEmail());
            admin.setNickname(newAdmin.getNickname());
            return this.adminService.saveAdmin(admin);
        }).orElseGet(() -> {
            newAdmin.setId(id);
            return this.adminService.saveAdmin(newAdmin);
        });
        return newAdmin;
    }
}
