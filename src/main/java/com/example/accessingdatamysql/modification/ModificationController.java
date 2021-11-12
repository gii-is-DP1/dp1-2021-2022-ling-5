package com.example.accessingdatamysql.modification;

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
public class ModificationController {
    @Autowired
    private ModificationService modificationService;

    @PostMapping(value = "/modifications") // Map ONLY POST Requests
    public @ResponseBody Modification addNewModification(@RequestBody Modification modification) {
        return this.modificationService.saveModification(modification);
    }

    @GetMapping(value = "/modifications")
    public @ResponseBody Iterable<Modification> getAllModifications() {
        return this.modificationService.findAllModifications();
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

    @PutMapping(value = "/modifications/{id}")
    public @ResponseBody Modification updateModification(@RequestBody Modification newModification,
            @PathVariable Long id) {
        this.modificationService.findModification(id).map(modification -> {
            modification.setDate(newModification.getDate());
            modification.setWhat(newModification.getWhat());
            modification.setBefore(newModification.getBefore());
            modification.setAfter(newModification.getAfter());
            return this.modificationService.saveModification(modification);
        }).orElseGet(() -> {
            newModification.setId(id);
            return this.modificationService.saveModification(newModification);
        });
        return newModification;
    }
}
