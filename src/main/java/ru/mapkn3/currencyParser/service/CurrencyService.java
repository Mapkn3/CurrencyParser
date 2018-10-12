package ru.mapkn3.currencyParser.service;

import ru.mapkn3.currencyParser.model.CurrenciesEntity;

import java.util.List;

public interface CurrencyService {
    CurrenciesEntity getCurrency(int id);

    CurrenciesEntity getCurrencyByName(String currency);

    List<CurrenciesEntity> getAllCurrencies();

    Integer addCurrency(CurrenciesEntity currency);

    void deleteCurrency(CurrenciesEntity currency);
}
