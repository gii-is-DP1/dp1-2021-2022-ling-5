package com.example.accessingdatamysql.result;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ResultServiceTests {
    @Autowired
    protected ResultService resultService;

    @Test
    @Transactional
    public void shouldInsertResult() {
        List<Result> results = this.resultService.findAllResults();
        int found = results.size();

        Result result = new Result("Minigame1:5, Minigame2:0, Minigame3:5", 10);
        this.resultService.saveResult(result);
        assertNotEquals(result.getId(), 0L);

        results = this.resultService.findAllResults();
        assertEquals(results.size(), found + 1);
    }

    @Test
    @Transactional(readOnly = true)
    public void shouldFindSingleResult() {
        Optional<Result> resultOpt = this.resultService.findResult(1L);
        if (resultOpt.isPresent()) {
            Result result = resultOpt.get();
            assertEquals(result.getTotalPoints(), 5);
        }
    }

    @Test
    @Transactional
    void shouldUpdateResult() {
        Optional<Result> result = this.resultService.findResult(1L);
        if (result.isPresent()) {
            String oldData = result.get().getData();
            String newData = oldData + "X";

            result.get().setData(newData);
            this.resultService.saveResult(result.get());

            result = this.resultService.findResult(1L);
            if (result.isPresent()) {
                assertEquals(result.get().getData(), newData);
            }
        }
    }

    @Test
    @Transactional
    void shouldDeleteResult() {
        Result result = new Result("Minigame1:5, Minigame2:10, Minigame3:5", 20);
        result = this.resultService.saveResult(result);
        List<Result> results = this.resultService.findAllResults();
        int found = results.size();

        this.resultService.deleteResult(result.getId());
        results = this.resultService.findAllResults();
        assertEquals(results.size(), found - 1);

    }

}
