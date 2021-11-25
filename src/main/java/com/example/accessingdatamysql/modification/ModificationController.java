package com.example.accessingdatamysql.modification;

import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.user.Admin;
import com.example.accessingdatamysql.user.AdminService;
import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerService;

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
public class ModificationController {
    @Autowired
    private ModificationService modificationService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/players/{playerId}/modifications") // Map ONLY POST Requests
    public @ResponseBody Modification addNewModificationToPlayer(@RequestBody Modification modification,
            @PathVariable Long playerId) {
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (player.isPresent()) {
            modification.setPlayer(player.get());
            return this.modificationService.saveModification(modification);
        }
        return null;
    }

    @PostMapping(value = "/admins/{adminId}/modifications") // Map ONLY POST Requests
    public @ResponseBody Modification addNewModificationToAdmin(@RequestBody Modification modification,
            @PathVariable Long adminId) {
        Optional<Admin> admin = this.adminService.findAdmin(adminId);
        if (admin.isPresent()) {
            modification.setAdmin(admin.get());
            return this.modificationService.saveModification(modification);
        }
        return null;
    }

    @GetMapping(value = "/modifications")
    public @ResponseBody Iterable<Modification> getAllModifications() {
        return this.modificationService.findAllModifications();
    }

    @GetMapping(value = "/players/{playerId}/modifications")
    public @ResponseBody List<Modification> getAllModificationsByPlayer(@PathVariable Long playerId) {
        return this.modificationService.findAllModificationsByPlayer(playerId);
    }

    @GetMapping(value = "/admins/{adminId}/modifications")
    public @ResponseBody List<Modification> getAllModificationsByAdmin(@PathVariable Long adminId) {
        return this.modificationService.findAllModificationsByAdmin(adminId);
    }

    @GetMapping(value = "/modifications/{id}")
    public @ResponseBody Optional<Modification> getModificationById(@PathVariable Long id) {
        return this.modificationService.findModification(id);
    }

    @DeleteMapping(value = "/modifications/{id}")
    public @ResponseBody String deleteModification(@PathVariable Long id) {
        this.modificationService.deleteModification(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/modifications")
    public @ResponseBody String deleteAllModifications() {
        this.modificationService.deleteAllModifications();
        return "Deleted all";
    }

    @DeleteMapping(value = "/players/{playerId}/modifications")
    public @ResponseBody String deleteAllModificationsByPlayer(@PathVariable Long playerId) {
        this.modificationService.deleteAllModificationsByPlayer(playerId);
        return "Deleted all";
    }

    @DeleteMapping(value = "/admins/{adminId}/modifications")
    public @ResponseBody String deleteAllModificationsByAdmin(@PathVariable Long adminId) {
        this.modificationService.deleteAllModificationsByAdmin(adminId);
        return "Deleted all";
    }

    @PutMapping(value = "/modifications/{id}")
    public @ResponseBody Modification updateModification(@RequestBody Modification newModification,
            @PathVariable Long id) {
        this.modificationService.findModification(id).map(modification -> {
            modification.setMoment(newModification.getMoment());
            modification.setWhat(newModification.getWhat());
            modification.setBeforeModification(newModification.getBeforeModification());
            modification.setAfterModification(newModification.getAfterModification());
            return this.modificationService.saveModification(modification);
        }).orElse(null);
        return null;
    }
}
