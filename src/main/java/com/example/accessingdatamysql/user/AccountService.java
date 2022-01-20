package com.example.accessingdatamysql.user;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Transactional
  public Account saveAccount(Account account) throws DataAccessException {
    accountRepository.save(account);
    return account;
  }

  public Optional<Account> findAccount(Long id) throws DataAccessException {
    return accountRepository.findById(id);
  }

  public Iterable<Account> findAllAccounts() throws DataAccessException {
    return accountRepository.findAll();
  }

  @Transactional
  public void deleteAccount(Long id) throws DataAccessException {
    accountRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllAccounts() throws DataAccessException {
    accountRepository.deleteAll();
  }
}
