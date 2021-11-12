package com.example.accessingdatamysql.result;

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
@RequestMapping(path = "/api")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @PostMapping(path = "/results") // Map ONLY POST Requests
    public @ResponseBody Result addNewResult(@RequestBody Result result) {
        return this.resultService.saveResult(result);
    }

    @GetMapping(path = "/results")
    public @ResponseBody Iterable<Result> getAllResults() {
        return this.resultService.findAllResults();
    }

    @GetMapping(path = "/results/{id}")
    public @ResponseBody Optional<Result> getResultById(@PathVariable Long id) {
        return this.resultService.findResult(id);
    }

    @DeleteMapping(path = "/results/{id}")
    public @ResponseBody String deleteResult(@PathVariable Long id) {
        this.resultService.deleteResult(id);
        return "Deleted";
    }

    @DeleteMapping(path = "/results")
    public @ResponseBody String deleteAllResults() {
        this.resultService.deleteAllResults();
        return "Deleted all";
    }

    @PutMapping("/results/{id}")
    public @ResponseBody Result updateResult(@RequestBody Result newResult, @PathVariable Long id) {
        this.resultService.findResult(id).map(result -> {
            result.setData(newResult.getData());
            return this.resultService.saveResult(result);
        }).orElseGet(() -> {
            newResult.setId(id);
            return this.resultService.saveResult(newResult);
        });
        return newResult;
    }
}
