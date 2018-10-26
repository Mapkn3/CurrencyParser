package ru.mapkn3.currencyParser.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;
import ru.mapkn3.currencyParser.repository.CurrenciesRepository;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    CurrenciesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public CurrenciesEntity getCurrency(int id) {
        log.debug("Getting currency with id=" + id);
        CurrenciesEntity currenciesEntity = repository.findById(id).orElse(null);
        if (currenciesEntity == null) {
            log.debug("Currency with id=" + id + " not found");
        }
        return currenciesEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public CurrenciesEntity getCurrencyByName(String currency) {
        log.debug("Getting currency with name: " + currency);
        CurrenciesEntity currenciesEntity = repository.findByCurrency(currency).orElse(null);
        if (currenciesEntity == null) {
            log.debug("Currency with name " + currency + " not found");
        }
        return currenciesEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrenciesEntity> getAllCurrencies() {
        List<CurrenciesEntity> currencies = repository.findAll();
        log.debug("Get " + currencies.size() + " currencies:");
        currencies.forEach(currency -> log.debug(currency.toString()));
        return currencies;
    }

    @Override
    public Integer addCurrency(CurrenciesEntity currency) {
        Integer id = repository.save(currency).getId();
        log.debug("Id of new currency: " + id);
        return id;
    }

    @Override
    public void deleteCurrency(CurrenciesEntity currency) {
        repository.delete(currency);
        log.debug("Delete currency: " + currency);
    }
}
