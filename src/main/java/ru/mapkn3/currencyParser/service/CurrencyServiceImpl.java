package ru.mapkn3.currencyParser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;
import ru.mapkn3.currencyParser.repository.CurrenciesRepository;

import java.util.List;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {
    private final static Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
    CurrenciesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public CurrenciesEntity getCurrency(int id) {
        logger.debug("Getting currency with id=" + id);
        CurrenciesEntity currenciesEntity = repository.findById(id).orElse(null);
        if (currenciesEntity == null) {
            logger.debug("Currency with id=" + id + " not found");
        }
        return currenciesEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public CurrenciesEntity getCurrencyByName(String currency) {
        logger.debug("Getting currency with name: " + currency);
        CurrenciesEntity currenciesEntity = repository.findByCurrency(currency).orElse(null);
        if (currenciesEntity == null) {
            logger.debug("Currency with name " + currency + " not found");
        }
        return currenciesEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrenciesEntity> getAllCurrencies() {
        List<CurrenciesEntity> currencies = repository.findAll();
        logger.debug("Get " + currencies.size() + " currencies:");
        currencies.forEach(currency -> logger.debug(currency.toString()));
        return currencies;
    }

    @Override
    public Integer addCurrency(CurrenciesEntity currency) {
        Integer id = repository.save(currency).getId();
        logger.debug("Id of new currency: " + id);
        return id;
    }

    @Override
    public void deleteCurrency(CurrenciesEntity currency) {
        repository.delete(currency);
        logger.debug("Delete currency: " + currency);
    }
}
