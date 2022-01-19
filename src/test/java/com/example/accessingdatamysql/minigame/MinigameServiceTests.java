package com.example.accessingdatamysql.minigame;

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
public class MinigameServiceTests {
    @Autowired
    protected MinigameService minigameService;

    @Test
    @Transactional
    public void shouldInsertMinigame() {
        List<Minigame> minigames = this.minigameService.findAllMinigames();
        int found = minigames.size();

        Minigame minigame = new Minigame("minigame4", "description4");
        this.minigameService.saveMinigame(minigame);
        assertNotEquals(minigame.getId(), 0L);

        minigames = this.minigameService.findAllMinigames();
        assertEquals(minigames.size(), found + 1);
    }

    @Test
    @Transactional(readOnly = true)
    public void shouldFindSingleMinigame() {
        Optional<Minigame> minigameOpt = this.minigameService.findMinigame(1L);
        if (minigameOpt.isPresent()) {
            Minigame minigame = minigameOpt.get();
            assertEquals(minigame.getName(), "Torre Infernal");
        }
    }

    @Test
    @Transactional
    void shouldUpdateMinigame() {
        Optional<Minigame> minigame = this.minigameService.findMinigame(1L);
        if (minigame.isPresent()) {
            String oldName = minigame.get().getName();
            String newName = oldName + "X";

            minigame.get().setName(newName);
            this.minigameService.saveMinigame(minigame.get());

            minigame = this.minigameService.findMinigame(1L);
            if (minigame.isPresent()) {
                assertEquals(minigame.get().getName(), newName);
            }
        }
    }

    @Test
    @Transactional
    void shouldDeleteMinigame() {
        Minigame minigame = new Minigame();
        minigame.setName("Foso");
        minigame = this.minigameService.saveMinigame(minigame);
        List<Minigame> minigames = this.minigameService.findAllMinigames();
        int found = minigames.size();

        this.minigameService.deleteMinigame(minigame.getId());
        minigames = this.minigameService.findAllMinigames();
        assertEquals(minigames.size(), found - 1);

    }

}
