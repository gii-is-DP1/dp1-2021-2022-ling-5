package com.example.accessingdatamysql.change;

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
public class ChangeController {
    @Autowired
    private ChangeService changeService;

    @RequestMapping(value = "/changes", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody Change addNewChange(@RequestBody Change change) {
        return this.changeService.saveChange(change);
    }

    @RequestMapping(value = "/changes", method = RequestMethod.GET)
    public @ResponseBody Iterable<Change> getAllChanges() {
        return this.changeService.findAllChanges();
    }

    @RequestMapping(value = "/changes/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Change> getChangeById(@PathVariable Long id) {
        return this.changeService.findChange(id);
    }

    @RequestMapping(value = "/changes/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteChange(@PathVariable Long id) {
        this.changeService.deleteChange(id);
        return "Deleted";
    }

    @RequestMapping(value = "/changes", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAllChanges() {
        this.changeService.deleteAllChanges();
        return "Deleted all";
    }

    @RequestMapping(value = "/changes/{id}", method = RequestMethod.PUT)
    public @ResponseBody Change updateChange(@RequestBody Change newChange, @PathVariable Long id) {
        this.changeService.findChange(id).map(change -> {
            change.setDate(newChange.getDate());
            change.setWhat(newChange.getWhat());
            change.setBefore(newChange.getBefore());
            change.setAfter(newChange.getAfter());
            return this.changeService.saveChange(change);
        }).orElseGet(() -> {
            newChange.setId(id);
            return this.changeService.saveChange(newChange);
        });
        return newChange;
    }
}
