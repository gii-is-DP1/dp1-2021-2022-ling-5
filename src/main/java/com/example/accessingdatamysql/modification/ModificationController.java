package com.example.accessingdatamysql.modification;

import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.user.Account;
import com.example.accessingdatamysql.user.AccountService;

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
    private AccountService accountService;

    @PostMapping(value = "accounts/{accountId}/modifications") // Map ONLY POST Requests
    public @ResponseBody Modification addNewModification(@RequestBody Modification modification,
            @PathVariable Long accountId) {
        Optional<Account> account = this.accountService.findAccount(accountId);
        if (account.isPresent()) {
            modification.setAccount(account.get());
            return this.modificationService.saveModification(modification);
        }
        return null;
    }

    @GetMapping(value = "/modifications")
    public @ResponseBody Iterable<Modification> getAllModifications() {
        return this.modificationService.findAllModifications();
    }

    @GetMapping(value = "accounts/{accountId}/modifications")
    public @ResponseBody List<Modification> getAllModificationsByAccount(@PathVariable Long accountId) {
        return this.modificationService.findAllModificationsByAccount(accountId);
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

    @DeleteMapping(value = "accounts/{accountId}/modifications")
    public @ResponseBody String deleteAllModificationsByAccount(@PathVariable Long accountId) {
        this.modificationService.deleteAllModificationsByAccount(accountId);
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
