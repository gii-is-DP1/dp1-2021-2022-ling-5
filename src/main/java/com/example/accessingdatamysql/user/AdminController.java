package com.example.accessingdatamysql.user;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.modification.Modification;
import com.example.accessingdatamysql.role.Role;
import com.example.accessingdatamysql.role.RoleService;

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

    @Autowired
    private FigureService figureService;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/admins") // Map ONLY POST Requests
    public @ResponseBody Admin addNewAdmin(@RequestBody Admin admin) {
        admin.setModifications(new ArrayList<Modification>());

        Optional<Role> role = this.roleService.findRole(2L);
        Optional<Figure> figure = this.figureService.findFigure(1L);
        if (figure.isPresent() && role.isPresent()) {
            admin.setFigure(figure.get());
            admin.setRole(role.get());
            return this.adminService.saveAdmin(admin);
        }
        return null;
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
            return this.adminService.saveAdmin(admin);
        }).orElse(null);
        return null;
    }

    @PutMapping(value = "/figures/{figureId}/admins/{adminId}")
    public @ResponseBody String updateFigureAdmin(@PathVariable Long adminId, @PathVariable Long figureId) {
        return this.figureService.findFigure(figureId).map(figure -> {
            Optional<Admin> optionalAdmin = this.adminService.findAdmin(adminId);
            if (optionalAdmin.isPresent()) {
                Admin admin = optionalAdmin.get();
                admin.setFigure(figure);
                this.adminService.saveAdmin(admin);
                return "Saved";
            } else {
                return "Admin not found";
            }
        }).orElse("Figure not found");
    }
}
