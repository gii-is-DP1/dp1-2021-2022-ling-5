package com.example.accessingdatamysql.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
public class AdminServiceTests {

  @Autowired
  protected AdminService adminService;

  @Test
  @Transactional
  public void shouldInsertAdmin() {
    List<Admin> admins = this.adminService.findAllAdmins();
    int found = admins.size();

    Admin admin = new Admin(
      "Unaco",
      "unaco",
      "unac",
      "unaco2@gmail.com",
      "unacon2"
    );
    this.adminService.saveAdmin(admin);
    assertNotEquals(admin.getId(), 0L);

    admins = this.adminService.findAllAdmins();
    assertEquals(admins.size(), found + 1);
  }

  @Test
  public void shouldFindSingleAdmin() {
    Optional<Admin> adminOpt = this.adminService.findAdmin(1L);
    if (adminOpt.isPresent()) {
      Admin admin = adminOpt.get();
      assertEquals(admin.getName(), "Unaco");
    }
  }

  @Test
  @Transactional
  void shouldUpdateAdmin() {
    Optional<Admin> admin = this.adminService.findAdmin(1L);
    if (admin.isPresent()) {
      String oldName = admin.get().getName();
      String newName = oldName + "X";

      admin.get().setName(newName);
      this.adminService.saveAdmin(admin.get());

      admin = this.adminService.findAdmin(1L);
      if (admin.isPresent()) {
        assertEquals(admin.get().getName(), newName);
      }
    }
  }

  @Test
  void shouldDeleteAdmin() {
    Admin admin = new Admin(
      "Dosaco",
      "dosaco",
      "dosaco",
      "dosaco@gmail.com",
      "dosacon"
    );
    admin = this.adminService.saveAdmin(admin);
    List<Admin> admins = this.adminService.findAllAdmins();
    int found = admins.size();

    this.adminService.deleteAdmin(admin.getId());
    admins = this.adminService.findAllAdmins();
    assertEquals(admins.size(), found - 1);
  }
}
