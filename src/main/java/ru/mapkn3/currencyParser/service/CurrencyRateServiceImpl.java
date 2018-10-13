package ru.mapkn3.currencyParser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.model.CurrencyRatesEntity;
import ru.mapkn3.currencyParser.repository.CurrencyRatesRepository;

import java.util.List;

@Service
@Transactional
public class CurrencyRateServiceImpl implements CurrencyRateService {
    private final static Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
    CurrencyRatesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public CurrencyRatesEntity getCurrencyRate(int id) {
        logger.debug("Getting currency rate with id=" + id);
        return repository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyRatesEntity> getCurrencyRatesByBank(BanksEntity bank) {
        logger.debug("Getting currency rate for bank: " + bank);
        return repository.findByBank(bank);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyRatesEntity> getAllCurrencyRates() {
        List<CurrencyRatesEntity> currencyRates = (List<CurrencyRatesEntity>) repository.findAll();
        logger.debug("Get " + currencyRates.size() + "currency rates:");
        currencyRates.forEach(currencyRate -> logger.debug(currencyRate.toString()));
        return currencyRates;
    }

    @Override
    public void deleteCurrencyRate(CurrencyRatesEntity currencyRate) {
        repository.delete(currencyRate);
        logger.debug("Delete currency rates: " + currencyRate);
    }
}
