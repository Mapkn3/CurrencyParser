package ru.mapkn3.currencyParser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrenciesRepository extends CrudRepository<CurrenciesEntity, Integer> {
    List<CurrenciesEntity> findAll();

    Optional<CurrenciesEntity> findByCurrency(String currency);
}
