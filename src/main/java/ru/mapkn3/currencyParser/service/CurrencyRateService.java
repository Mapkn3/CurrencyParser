package ru.mapkn3.currencyParser.service;

import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;
import ru.mapkn3.currencyParser.model.CurrencyRatesEntity;

import java.sql.Timestamp;
import java.util.List;

public interface CurrencyRateService {
    CurrencyRatesEntity getCurrencyRate(int id);

    List<CurrencyRatesEntity> getCurrencyRatesByBank(BanksEntity bank);

    List<CurrencyRatesEntity> getCurrencyRatesByCurrency(CurrenciesEntity currency);

    List<CurrencyRatesEntity> getAllCurrencyRates();

    void deleteCurrencyRate(CurrencyRatesEntity currencyRate);

    List<CurrencyRatesEntity> getAllCurrencyRateByCurrencyAndBankAndDateBetween(CurrenciesEntity currenciesEntity, BanksEntity banksEntity, Timestamp startTime, Timestamp endTime);

    CurrencyRatesEntity parseCurrencyRateForBank(int currencyId, int bankId);

    CurrencyRatesEntity getTheBestActualCurrencyRateByCurrency(CurrenciesEntity currency);
}
