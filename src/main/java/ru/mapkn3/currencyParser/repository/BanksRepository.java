package ru.mapkn3.currencyParser.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mapkn3.currencyParser.model.BanksEntity;

import java.util.List;

public interface BanksRepository extends CrudRepository<BanksEntity, Integer> {
    List<BanksEntity> findAll();

    List<BanksEntity> findAllByParsingIsTrue();
}
