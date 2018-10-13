package ru.mapkn3.currencyParser.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;

import java.util.List;

public interface CurrenciesRepository extends CrudRepository<CurrenciesEntity, Integer> {
    List<CurrenciesEntity> findAll();

    CurrenciesEntity findByCurrency(String currency);
}
