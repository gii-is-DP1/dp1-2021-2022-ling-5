package com.example.accessingdatamysql.PlayerFigures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.accessingdatamysql.playerfigures.PlayerFigures;
import com.example.accessingdatamysql.playerfigures.PlayerFiguresService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerFiguresServiceTests {

    @Autowired
    protected PlayerFiguresService playerFiguresService;

    @Test
    @Transactional
    public void shouldInsertPlayerFigures() {
        List<PlayerFigures> pfs = this.playerFiguresService.findAllPlayerFigures();
        int found = pfs.size();

        PlayerFigures pf = new PlayerFigures(33);
        this.playerFiguresService.savePlayerFigures(pf);
        assertNotEquals(pf.getId(), 0L);

        pfs = this.playerFiguresService.findAllPlayerFigures();
        assertEquals(pfs.size(), found + 1);
    }

    @Test
    public void shouldFindSinglePlayerFigures() {
        Optional<PlayerFigures> pfOpt = this.playerFiguresService.findPlayerFigures(1L);
        if (pfOpt.isPresent()) {
            PlayerFigures pf = pfOpt.get();
            assertEquals(pf.getSuccesful(), 33);
        }
    }

    @Test
    void shouldDeleteResult() {
        PlayerFigures pf = new PlayerFigures(20);
        pf = this.playerFiguresService.savePlayerFigures(pf);
        List<PlayerFigures> pfs = this.playerFiguresService.findAllPlayerFigures();
        int found = pfs.size();

        this.playerFiguresService.deletePlayerFigures(pf.getId());
        pfs = this.playerFiguresService.findAllPlayerFigures();
        assertEquals(pfs.size(), found - 1);

    }
    
}
