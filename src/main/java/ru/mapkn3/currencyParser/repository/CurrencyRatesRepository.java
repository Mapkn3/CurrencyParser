package ru.mapkn3.currencyParser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;
import ru.mapkn3.currencyParser.model.CurrencyRatesEntity;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CurrencyRatesRepository extends CrudRepository<CurrencyRatesEntity, Integer> {
    List<CurrencyRatesEntity> findAllByBank(BanksEntity banksEntity);

    List<CurrencyRatesEntity> findAllByCurrency(CurrenciesEntity currenciesEntity);

    List<CurrencyRatesEntity> findAllByCurrencyAndBankAndCurrencyUpdateTimeBetweenOrderByCurrencyUpdateTimeDesc(CurrenciesEntity currenciesEntity, BanksEntity banksEntity, Timestamp startTime, Timestamp endTime);

    CurrencyRatesEntity findDistinctFirstByCurrencyOrderByPurchaseRateAscCurrencyUpdateTimeDesc(CurrenciesEntity currenciesEntity);
}
