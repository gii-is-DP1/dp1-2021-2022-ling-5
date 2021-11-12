package com.example.accessingdatamysql.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public Optional<Account> findAccount(Long id) {
        return accountRepository.findById(id);
    }

    public Iterable<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }

}
