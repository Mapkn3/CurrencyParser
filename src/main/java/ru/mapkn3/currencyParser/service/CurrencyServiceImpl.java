package ru.mapkn3.currencyParser.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;
import ru.mapkn3.currencyParser.repository.CurrenciesRepository;

import java.util.List;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {
    private final static Logger logger = Logger.getLogger(BankServiceImpl.class);

    @Autowired
    CurrenciesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public CurrenciesEntity getCurrency(int id) {
        logger.debug("Getting currency with id=" + id);
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CurrenciesEntity getCurrencyByName(String currency) {
        logger.debug("Getting currency with name: " + currency);
        return repository.findByCurrency(currency);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrenciesEntity> getAllCurrencies() {
        List<CurrenciesEntity> currencies = repository.findAll();
        logger.debug("Get " + currencies.size() + " currencies:");
        currencies.forEach(logger::debug);
        return currencies;
    }

    @Override
    public Integer addCurrency(CurrenciesEntity currency) {
        Integer id = repository.save(currency);
        logger.debug("Id of new currency: " + id);
        return id;
    }

    @Override
    public void deleteCurrency(CurrenciesEntity currency) {
        repository.delete(currency);
        logger.debug("Delete currency: " + currency);
    }
}
