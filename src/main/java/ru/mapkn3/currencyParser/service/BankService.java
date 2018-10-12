package ru.mapkn3.currencyParser.service;

import ru.mapkn3.currencyParser.model.BanksEntity;

import java.util.List;

public interface BankService {
    BanksEntity getBank(int id);

    List<BanksEntity> getAllBanks();

    Integer addNewBank(BanksEntity bank);

    BanksEntity updateBank(BanksEntity bank);

    void deleteBank(BanksEntity bank);
}
