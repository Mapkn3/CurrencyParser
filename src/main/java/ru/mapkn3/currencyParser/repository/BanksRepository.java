package ru.mapkn3.currencyParser.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mapkn3.currencyParser.model.BanksEntity;

public interface BanksRepository extends CrudRepository<BanksEntity, Integer> {
}
