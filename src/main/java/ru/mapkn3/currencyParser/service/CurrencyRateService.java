package ru.mapkn3.currencyParser.service;

import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.model.CurrencyRatesEntity;

import java.util.List;

public interface CurrencyRateService {
    CurrencyRatesEntity getCurrencyRate(int id);

    List<CurrencyRatesEntity> getCurrencyRatesByBank(BanksEntity bank);

    List<CurrencyRatesEntity> getAllCurrencyRates();

    void deleteCurrencyRate(CurrencyRatesEntity currencyRate);
}
