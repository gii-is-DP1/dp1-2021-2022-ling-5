package com.example.accessingdatamysql.result;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    private ResultRepository resultRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Transactional
    public Result saveResult(Result result) throws DataAccessException {
        resultRepository.save(result);
        return result;
    }

    public Optional<Result> findResult(Long id) {
        return resultRepository.findById(id);
    }

    public Iterable<Result> findAllResults() {
        return resultRepository.findAll();
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    public void deleteAllResults() {
        resultRepository.deleteAll();
    }
}
