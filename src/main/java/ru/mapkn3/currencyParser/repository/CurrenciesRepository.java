package ru.mapkn3.currencyParser.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;

import java.util.List;
import java.util.Optional;

public interface CurrenciesRepository extends CrudRepository<CurrenciesEntity, Integer> {
    List<CurrenciesEntity> findAll();

    Optional<CurrenciesEntity> findByCurrency(String currency);
}
