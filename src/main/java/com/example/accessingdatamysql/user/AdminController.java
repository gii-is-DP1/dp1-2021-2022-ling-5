package com.example.accessingdatamysql.user;

import java.util.Optional;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.role.Role;
import com.example.accessingdatamysql.role.RoleService;

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
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private FigureService figureService;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/admins")
    public @ResponseBody Admin addNewAdmin(@RequestBody Admin admin) {

        Optional<Role> role = this.roleService.findRole(2L);
        Optional<Figure> figure = this.figureService.findFigure(1L);
        if (!figure.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Figure not found");
        if (!role.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        try {
            admin.setFigure(figure.get());
            admin.setRole(role.get());
            return this.adminService.saveAdmin(admin);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping(value = "/admins")
    public @ResponseBody Iterable<Admin> getAllAdmins() {
        return this.adminService.findAllAdmins();
    }

    @GetMapping(value = "/admins/{id}")
    public @ResponseBody Admin getAdminById(@PathVariable Long id) {
        Optional<Admin> aOptional = this.adminService.findAdmin(id);
        if (aOptional.isPresent())
            return aOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
    }

    @DeleteMapping(value = "/admins/{id}")
    public @ResponseBody void deleteAdmin(@PathVariable Long id) {
        Optional<Admin> aOptional = this.adminService.findAdmin(id);
        if (aOptional.isPresent()) {
            this.adminService.deleteAdmin(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Admin deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
    }

    @DeleteMapping(value = "/admins")
    public @ResponseBody void deleteAllAdmins() {
        this.adminService.deleteAllAdmins();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Admins deleted");
    }

    @PutMapping(value = "/admins/{id}")
    public @ResponseBody Admin updateAdmin(@RequestBody Admin newAdmin, @PathVariable Long id) {
        Optional<Admin> aOptional = this.adminService.findAdmin(id);
        if (!aOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        try {
            Admin admin = aOptional.get();
            admin.setName(newAdmin.getName());
            admin.setSurname(newAdmin.getSurname());
            return this.adminService.saveAdmin(admin);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/figures/{figureId}/admins/{adminId}")
    public @ResponseBody Admin updateFigureAdmin(@PathVariable Long adminId, @PathVariable Long figureId) {
        Optional<Figure> fOptional = this.figureService.findFigure(figureId);
        if (!fOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Figure not found");
        Figure figure = fOptional.get();
        Optional<Admin> optionalAdmin = this.adminService.findAdmin(adminId);
        if (!optionalAdmin.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        try {
            Admin admin = optionalAdmin.get();
            admin.setFigure(figure);
            return this.adminService.saveAdmin(admin);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
