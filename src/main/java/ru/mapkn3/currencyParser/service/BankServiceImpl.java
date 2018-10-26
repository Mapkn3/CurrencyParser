package ru.mapkn3.currencyParser.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.repository.BanksRepository;

import java.util.List;

@Slf4j
@Service
@Transactional
public class BankServiceImpl implements BankService {
    @Autowired
    BanksRepository repository;

    @Override
    @Transactional(readOnly = true)
    public BanksEntity getBank(int id) {
        log.debug("Getting bank with id=" + id);
        BanksEntity banksEntity = repository.findById(id).orElse(null);
        if (banksEntity == null) {
            log.debug("Bank with id=" + id + " not found");
        }
        return banksEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BanksEntity> getAllBanks() {
        List<BanksEntity> banks = repository.findAll();
        log.debug("Get " + banks.size() + " banks:");
        banks.forEach(bank -> log.debug(bank.toString()));
        return banks;
    }

    @Override
    public List<BanksEntity> getParsingBanks() {
        List<BanksEntity> banks = repository.findAllByParsingIsTrue();
        log.debug("Get " + banks.size() + " banks ready for parsing:");
        banks.forEach(bank -> log.debug(bank.toString()));
        return banks;
    }

    @Override
    public Integer addNewBank(BanksEntity bank) {
        Integer id = repository.save(bank).getId();
        log.debug("Id of new bank: " + id);
        return id;
    }

    @Override
    public BanksEntity updateBank(BanksEntity bank) {
        BanksEntity updatedBank = repository.save(bank);
        log.debug("Updated bank: " + updatedBank);
        return updatedBank;
    }

    @Override
    public void deleteBank(BanksEntity bank) {
        repository.delete(bank);
        log.debug("Delete bank: " + bank);
    }
}
