package ru.mapkn3.currencyParser.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.model.CurrencyRatesEntity;

import java.util.List;

public interface CurrencyRatesRepository extends CrudRepository<CurrencyRatesEntity, Integer> {
    List<CurrencyRatesEntity> findByBank(BanksEntity banksEntity);
}
