package ru.mapkn3.currencyParser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.repository.BanksRepository;

import java.util.List;

@Service
@Transactional
public class BankServiceImpl implements BankService {
    private final static Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
    BanksRepository repository;

    @Override
    @Transactional(readOnly = true)
    public BanksEntity getBank(int id) {
        logger.debug("Getting bank with id=" + id);
        return repository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BanksEntity> getAllBanks() {
        List<BanksEntity> banks = (List<BanksEntity>) repository.findAll();
        logger.debug("Get " + banks.size() + " banks:");
        banks.forEach(bank -> logger.debug(bank.toString()));
        return banks;
    }

    @Override
    public Integer addNewBank(BanksEntity bank) {
        Integer id = repository.save(bank).getId();
        logger.debug("Id of new bank: " + id);
        return id;
    }

    @Override
    public BanksEntity updateBank(BanksEntity bank) {
        BanksEntity updatedBank = repository.save(bank);
        logger.debug("Updated bank: " + updatedBank);
        return updatedBank;
    }

    @Override
    public void deleteBank(BanksEntity bank) {
        repository.delete(bank);
        logger.debug("Delete bank: " + bank);
    }
}
