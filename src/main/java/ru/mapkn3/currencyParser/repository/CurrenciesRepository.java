package ru.mapkn3.currencyParser.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;

public interface CurrenciesRepository extends CrudRepository<CurrenciesEntity, Integer> {
    CurrenciesEntity findByCurrency(String currency);
}
