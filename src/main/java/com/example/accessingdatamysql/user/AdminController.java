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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/admins") // Map ONLY POST Requests
    public @ResponseBody Admin addNewAdmin(@RequestBody Admin admin) {
        return this.adminService.saveAdmin(admin);
    }

    @GetMapping(value = "/admins")
    public @ResponseBody Iterable<Admin> getAllAdmins() {
        return this.adminService.findAllAdmins();
    }

    @GetMapping(value = "/admins/{id}")
    public @ResponseBody Optional<Admin> getAdminById(@PathVariable Long id) {
        return this.adminService.findAdmin(id);
    }

    @DeleteMapping(value = "/admins/{id}")
    public @ResponseBody String deleteAdmin(@PathVariable Long id) {
        this.adminService.deleteAdmin(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/admins")
    public @ResponseBody String deleteAllAdmins() {
        this.adminService.deleteAllAdmins();
        return "Deleted all";
    }

    @PutMapping(value = "/admins/{id}")
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
