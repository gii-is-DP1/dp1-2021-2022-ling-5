package com.example.accessingdatamysql.modification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ModificationServiceTests {
    @Autowired
    protected ModificationService modificationService;

    @Test
    @Transactional
    public void shouldInsertModification() {
        List<Modification> modifications = this.modificationService.findAllModifications();
        int found = modifications.size();

        Modification modification = new Modification(new Date(System.currentTimeMillis()), "cambio2", "DOS", "dos");
        this.modificationService.saveModification(modification);
        assertNotEquals(modification.getId(), 0L);

        modifications = this.modificationService.findAllModifications();
        assertEquals(modifications.size(), found + 1);
    }

    @Test
    public void shouldFindSingleModification() {
        Optional<Modification> modificationOpt = this.modificationService.findModification(1L);
        if (modificationOpt.isPresent()) {
            Modification modification = modificationOpt.get();
            assertEquals(modification.getWhat(), "cambio1");
        }
    }

    @Test
    @Transactional
    void shouldUpdateModification() {
        Optional<Modification> modification = this.modificationService.findModification(1L);
        if (modification.isPresent()) {
            String oldWhat = modification.get().getWhat();
            String newWhat = oldWhat + "X";

            modification.get().setWhat(newWhat);
            this.modificationService.saveModification(modification.get());

            modification = this.modificationService.findModification(1L);
            if (modification.isPresent()) {
                assertEquals(modification.get().getWhat(), newWhat);
            }
        }
    }

    @Test
    void shouldDeleteModification() {
        Modification modification = new Modification(new Date(System.currentTimeMillis()), "cambio3", "TRES", "tres");
        modification = this.modificationService.saveModification(modification);
        List<Modification> modifications = this.modificationService.findAllModifications();
        int found = modifications.size();

        this.modificationService.deleteModification(modification.getId());
        modifications = this.modificationService.findAllModifications();
        assertEquals(modifications.size(), found - 1);

    }

}
